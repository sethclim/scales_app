package sheridan.climense.kmmsharedmodule.domain

import sheridan.climense.kmmsharedmodule.domain.model.Practice
import kotlin.random.Random


//probably don't need anymore
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


