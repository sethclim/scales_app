package sheridan.climense.scales_app2.model

import java.io.Serializable
import java.util.*

data class PracticePackage (
        val routine_name : String,
        val practice_array : Array<RoutineGenerator.Companion.practice>,
        val savedPractice : Boolean,
        val key : Long = -1,
        val total : Int = 0,
        val date : Date = Date(1)
) : Serializable