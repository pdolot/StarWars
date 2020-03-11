package com.pdolecinski.myapplication

import com.pdolecinski.myapplication.exercise.Time
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeTest {
    @Test
    fun thereIsABreakNow() {
        assertEquals(Time.checkIfIsTimeForBreak(12, 0, 15), true)
    }

    @Test
    fun thereIsABreakNow2() {
        assertEquals(Time.checkIfIsTimeForBreak(8, 22, 15), true)
    }

    @Test
    fun thereIsNotABreakNow() {
        assertEquals(Time.checkIfIsTimeForBreak(15, 23, 10), false)
    }

    @Test
    fun thereIsNotABreakNow2() {
        assertEquals(Time.checkIfIsTimeForBreak(7, 22, 6), false)
    }
}
