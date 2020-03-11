package com.pdolecinski.starWars.content.customView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.pdolecinski.starWars.R
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CircleProgressBars @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private val thumbPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private var strokeWidth = 0
        set(value) {
            field = value
            textPaint.textSize = field.toFloat() / 2f
            paint.strokeWidth = field.toFloat()
        }

    private var strokeCap = 0
        set(value) {
            field = value
            paint.strokeCap = when (field) {
                0 -> Paint.Cap.ROUND
                1 -> Paint.Cap.BUTT
                2 -> Paint.Cap.SQUARE
                else -> Paint.Cap.ROUND
            }
        }

    private var progressCount = 1
    private var maxProgressAngle = 360
        set(value) {
            field = value
            startAngle = 90f + ((360f - field) / 2f)
        }
    private var startAngle = 0f

    private var spaceBetween = 0f

    private var bounds = ArrayList<RectF>()
    private var thumbs = ArrayList<RectF>()

    private var colorsRef: TypedArray? = null
    private var colors = ArrayList<Int>()

    private var bgColorsRef: TypedArray? = null
    private var bgColors = ArrayList<Int>()

    private var progressesRef: TypedArray? = null
    private var progresses = ArrayList<Float>()

    private var progressesLabelsRef: TypedArray? = null
    private var progressesLabels = ArrayList<String>()

    private var isBackgroundEnabled = true
    var isTextEnabled = true

    var currentThumbIndex: Int? = null

    var typeface: Typeface? = null
        set(value) {
            field = value
            textPaint.typeface = field
        }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleProgressBars,
            defStyleAttr,
            0
        )
        strokeWidth = a.getDimensionPixelSize(R.styleable.CircleProgressBars_strokeWidth, 0)
        spaceBetween =
            a.getDimensionPixelSize(R.styleable.CircleProgressBars_spaceBetween, 0).toFloat()
        strokeCap = a.getInteger(R.styleable.CircleProgressBars_strokeCap, 0)
        progressCount = a.getInteger(R.styleable.CircleProgressBars_count, 1)
        maxProgressAngle = a.getInteger(R.styleable.CircleProgressBars_maxProgressAngle, 360)
        isBackgroundEnabled = a.getBoolean(R.styleable.CircleProgressBars_backgroundEnabled, true)
        isTextEnabled = a.getBoolean(R.styleable.CircleProgressBars_textEnabled, true)

        colorsRef = resources.obtainTypedArray(
            a.getResourceId(
                R.styleable.CircleProgressBars_colors,
                R.array.colors
            )
        )

        if (isBackgroundEnabled) {
            bgColorsRef = resources.obtainTypedArray(
                a.getResourceId(
                    R.styleable.CircleProgressBars_bgColors,
                    R.array.bgColors
                )
            )

            bgColorsRef?.let {
                for (i in 0 until progressCount) {
                    bgColors.add(it.getColor(i % it.length(), 0))
                }
            }
        }

        if (isTextEnabled) {
            progressesLabelsRef = resources.obtainTypedArray(
                a.getResourceId(
                    R.styleable.CircleProgressBars_progressesLabels,
                    R.array.progressLabel
                )
            )

            progressesLabelsRef?.let {
                for (i in 0 until progressCount) {
                    progressesLabels.add(it.getString(i % it.length()) ?: "")
                }
            }
        }

        progressesRef = resources.obtainTypedArray(
            a.getResourceId(
                R.styleable.CircleProgressBars_progresses,
                R.array.emptyProgresses
            )
        )

        colorsRef?.let {
            for (i in 0 until progressCount) {
                colors.add(it.getColor(i % it.length(), 0))
            }
        }



        progressesRef?.let {
            for (i in 0 until progressCount) {
                val progress = it.getFloat(i % it.length(), 0f)
                progresses.add(if (progress > 1f) 1f else if (progress <= 0) 0.001f else progress)
            }
        }

        a.recycle()
        colorsRef?.recycle()
        bgColorsRef?.recycle()
        progressesRef?.recycle()

        for (i in 0 until progressCount) {
            thumbs.add(RectF())
        }

        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentThumbIndex = getThumb(event.x, event.y)
                    currentThumbIndex != null
                }
                MotionEvent.ACTION_MOVE -> {
                    currentThumbIndex?.let { setProgressFromAngle(event.x, -event.y, it) }
                    currentThumbIndex != null
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    currentThumbIndex = null
                    true
                }
                else -> false
            }
        }
    }

    private fun setProgressFromAngle(x: Float, y: Float, thumbIndex: Int) {
        val localX = x - (measuredWidth / 2.0)
        val localY = y + (measuredHeight / 2.0)

        val atan = Math.toDegrees(atan2(localY, localX))
        var angle = if (atan > 0) 360 - atan else -atan

        var sweepAngle = if (angle < startAngle) 360.0 + angle else angle
        sweepAngle -= startAngle

        var progress = sweepAngle.toFloat() / maxProgressAngle

        if (progress > 1.0f && angle > 90) {
            progress = 0f
        } else if (progress > 1.0f && angle < 90) {
            progress = 1f
        }

        setProgress(progress, thumbIndex)

    }

    private fun getThumb(x: Float, y: Float): Int? {
        var i = 0
        thumbs.forEach {
            if (x in it.left..it.right && y in it.top..it.bottom) {
                return i
            }
            i++
        }
        return null
    }

    fun setProgress(progress: Float, position: Int) {
        if (position > progresses.size - 1) return
        progresses[position] = if (progress > 1f) 1f else if (progress <= 0) 0.001f else progress
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val spec =
            if (measuredHeight > measuredWidth) widthMeasureSpec else if (measuredWidth > measuredHeight) heightMeasureSpec else widthMeasureSpec
        val min = min(measuredWidth, measuredHeight)

        setMeasuredDimension(
            measureDimension(min, spec),
            measureDimension(min, spec)
        )

        measureBounds()
    }

    private fun measureBounds() {
        for (i in 0..progressCount) {
            val space = strokeWidth / 2f + (i * (strokeWidth + spaceBetween))
            bounds.add(
                RectF(
                    space + paddingStart,
                    space + paddingTop,
                    measuredWidth.toFloat() - space - paddingEnd,
                    measuredHeight.toFloat() - space - paddingBottom
                )
            )
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val path = Path()

        for (i in 0 until progressCount) {

            if (isBackgroundEnabled) {
                path.reset()
                path.addArc(bounds[i], startAngle, maxProgressAngle.toFloat())
                canvas.drawPath(path, paint.apply {
                    color = bgColors[i]
                })
            }

            path.reset()
            path.addArc(bounds[i], startAngle, maxProgressAngle * progresses[i])
            canvas.drawPath(path, paint.apply {
                color = colors[i]
            })

            if (isTextEnabled && checkIfTextCanBeDraw(
                    progressesLabels[i].length,
                    maxProgressAngle * progresses[i],
                    bounds[i]
                )
            ) {
                canvas.drawTextOnPath(
                    progressesLabels[i],
                    path,
                    0f,
                    (strokeWidth / 4f) - 4f,
                    textPaint
                )
            }

            if (progresses[i] > 0) {
                measureThumbPosition(i, bounds[i], startAngle, maxProgressAngle * progresses[i])
                canvas.drawOval(thumbs[i], thumbPaint.apply {
                    color = Color.parseColor("#32000000")
                })
            }
        }
    }

    private fun checkIfTextCanBeDraw(textLength: Int, alpha: Float, bound: RectF): Boolean {
        val radius = (bound.bottom - bound.top) / 2
        val l = alpha / 360f * 2 * Math.PI * radius
        return textPaint.textSize * textLength < l
    }

    private fun measureThumbPosition(
        position: Int,
        rect: RectF,
        startAngle: Float,
        sweepAngle: Float
    ) {
        val centerX = width / 2f
        val centerY = height / 2f

        val radius = (rect.bottom - rect.top) / 2

        val thumbXPosition =
            centerX + (radius * cos(Math.toRadians(startAngle.toDouble() + sweepAngle.toDouble()))).toInt()
        val thumbYPosition =
            centerY + (radius * sin(Math.toRadians(startAngle.toDouble() + sweepAngle.toDouble()))).toInt()

        thumbs[position] = RectF(
            thumbXPosition - strokeWidth / 3f,
            thumbYPosition - strokeWidth / 3f,
            thumbXPosition + strokeWidth / 3f,
            thumbYPosition + strokeWidth / 3f
        )
    }

    fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }
}