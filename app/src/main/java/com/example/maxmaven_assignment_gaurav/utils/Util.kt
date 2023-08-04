package com.example.maxmaven_assignment_gaurav.utils

object Util {

    fun isPrimeNumber(num: Int): Boolean {
        var i = 2
        var flag = false
        while (i <= num / 2) {
            if (num % i == 0) {
                flag = true
                break

            }
            ++i
        }
        return !flag
    }
}