package com.pdolecinski.myapplication.exercise

object Time {
    fun checkIfIsTimeForBreak(currentTime: Int, startTime: Int, endTime: Int) =
        startTime - (if (startTime >= endTime) 24 else 0) <= currentTime && currentTime < endTime ||
                startTime <= currentTime && currentTime < endTime + (if (startTime >= endTime) 24 else 0)
}
