package pwr.jakubgruda.domain

data class TtpProblemDescription(
        val problemName: String,
        val knapsackDataType: String,
        val dimension: Int,
        val numberOfItems: Int,
        val capacityOfKnapsack: Int,
        val minSpeed: Double,
        val maxSpeed: Double,
        val rentingRatio: Double,
        val edgeWeightType: EdgeWeightType
)
