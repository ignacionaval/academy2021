package com.example.unittest

import kotlin.math.ceil

class ParkingMachine {
    fun calculateCost(minutes: Int): Int {
        if (minutes < 0) {
            throw Exception("Invalid number")
        }

        if (minutes <= MAX_TIME_OF_FREE_COST) {
            return 0
        }

        if (minutes > MAX_ADMITTED_TIME) {
            throw GoToManagementException()
        }

        return ceil(minutes/30.0).toInt() * COST_OF_HALF_HOUR
    }

    companion object {
        const val MAX_TIME_OF_FREE_COST = 20
        const val COST_OF_HALF_HOUR = 20
        const val MAX_ADMITTED_TIME = 180
    }
}

class GoToManagementException : Exception("Mucho tiempo! ve a Administración")