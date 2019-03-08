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
        val edgeWeightType: EdgeWeightType,
        val cities: List<City>,
        val items: List<Item>
) {

    override fun toString(): String {
        val citiesString = "Cities:\n${cities.joinToString("\n")}"
        val itemsString = "Items:\n${items.joinToString("\n")}"

        return "TtpProblemDescription(problemName='$problemName', knapsackDataType='$knapsackDataType', dimension=$dimension, numberOfItems=$numberOfItems, capacityOfKnapsack=$capacityOfKnapsack, minSpeed=$minSpeed, maxSpeed=$maxSpeed, rentingRatio=$rentingRatio, edgeWeightType=$edgeWeightType)" +
                "\n$citiesString\n$itemsString"

    }
}
