package pwr.jakubgruda.domain

import kotlin.math.pow

data class City(val index: Int, val x: Double, val y: Double) {

    fun distanceTo(other: City) = Math.sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
}
