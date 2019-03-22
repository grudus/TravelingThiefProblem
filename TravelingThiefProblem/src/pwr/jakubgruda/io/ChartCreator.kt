package pwr.jakubgruda.io

import java.io.File
import java.util.concurrent.TimeUnit

class ChartCreator(private val chartDefinitionPath: String) {

    fun create(data: File) {
        Runtime.getRuntime()
                .exec("c3-chart-maker ${data.absolutePath} --chart=$chartDefinitionPath --out=${data.absolutePath.replace("csv", "png")}")
                .waitFor(10, TimeUnit.SECONDS)
    }

    fun isCreationAvailable(): Boolean {
        val process = Runtime.getRuntime()
                .exec("c3-chart-maker -v")

        process.waitFor(10, TimeUnit.SECONDS)
        return process.exitValue() == 0
    }
}
