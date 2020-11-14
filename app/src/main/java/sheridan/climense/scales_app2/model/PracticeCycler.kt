package sheridan.climense.scales_app2.model

import kotlin.random.Random


class PracticeCycler {
    companion object{
        var practiceArray : MutableList<RoutineGenerator.Companion.practice> = mutableListOf()

        var currentScale = RoutineGenerator.Companion.practice("-1", "-1", "-1")

        fun nextScale(){
            val index = Random.nextInt(0, practiceArray.size)
            val item = practiceArray[index]
            currentScale = item
            practiceArray.removeAt(index)
        }
    }
}


