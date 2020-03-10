package com.pdolecinski.myapplication.content.page.films

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdolecinski.myapplication.R
import com.pdolecinski.myapplication.data.model.app.Film
import kotlinx.android.synthetic.main.item_film.view.*

class FilmsAdapter : RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    private var items: List<Film>? = null
    var showDetailsClickListener: (id: Long) -> Unit = {}

    fun setData(items: List<Film>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.itemView.apply {
            title.text = item?.title
            release_date.text = item?.release_date

            showDetails.setOnClickListener {
                item?.episode_id?.let {id ->
                    showDetailsClickListener(id)
                }

            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}