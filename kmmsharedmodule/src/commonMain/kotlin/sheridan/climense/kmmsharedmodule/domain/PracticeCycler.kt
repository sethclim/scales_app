package sheridan.climense.kmmsharedmodule.domain

import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.Scale
import sheridan.climense.kmmsharedmodule.model.TechTypes
import kotlin.random.Random


class PracticeCycler {
    companion object{
        var practiceArray : MutableList<Practice> = mutableListOf()

        var currentScale : Practice? = null

        fun nextScale(){
            val index = Random.nextInt(0, practiceArray.size)
            val item = practiceArray[index]
            currentScale = item
            practiceArray.removeAt(index)
        }
    }
}


