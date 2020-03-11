package com.pdolecinski.starWars

import com.pdolecinski.starWars.exercise.Time
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeTest {
    @Test
    fun thereIsABreakNow() {
        assertEquals(true, Time.checkIfIsTimeForBreak(12, 0, 15))
    }

    @Test
    fun thereIsABreakNow2() {
        assertEquals(true, Time.checkIfIsTimeForBreak(8, 22, 15) )
    }

    @Test
    fun thereIsNotABreakNow() {
        assertEquals(false, Time.checkIfIsTimeForBreak(15, 23, 10))
    }

    @Test
    fun thereIsNotABreakNow2() {
        assertEquals(false, Time.checkIfIsTimeForBreak(7, 22, 6))
    }
}
