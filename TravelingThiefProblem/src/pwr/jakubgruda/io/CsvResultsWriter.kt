package pwr.jakubgruda.io

import java.io.File

class CsvResultsWriter(private val file: File) {

    fun writeHeaders() {
        file.writeText("Generation,Best,Average,Worst")
    }

    fun appendData(generation: Int, best: Double, avg: Double, worst: Double) {
        file.appendText("\n$generation,$best,$avg,$worst")
    }
}
