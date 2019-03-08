package pwr.jakubgruda

import pwr.jakubgruda.io.TtpFileLoader
import java.io.File

private const val LEVEL = "hard"
private const val FILE_PATH = "TravelingThiefProblem/res/${LEVEL}_0.ttp"

fun main(args: Array<String>) {
    println("__ Traveling Thief Problem __")
    println(TtpFileLoader().load(File(FILE_PATH)))
}
