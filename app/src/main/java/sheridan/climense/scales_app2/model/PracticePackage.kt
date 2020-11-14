package sheridan.climense.scales_app2.model

import java.io.Serializable

data class PracticePackage (val routine_name : String, val practice_array : Array<RoutineGenerator.Companion.practice>) : Serializable