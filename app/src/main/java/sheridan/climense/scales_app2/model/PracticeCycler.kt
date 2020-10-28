package sheridan.climense.scales_app2.model

import kotlin.random.Random

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */

class PracticeCycler {
    companion object{
        var practiceArray : MutableList<String> = mutableListOf()

        var currentScale = ""

        fun nextScale(){
            val index = Random.nextInt(0, practiceArray.size)
            currentScale = practiceArray[index]
            practiceArray.removeAt(index)
        }
    }
}