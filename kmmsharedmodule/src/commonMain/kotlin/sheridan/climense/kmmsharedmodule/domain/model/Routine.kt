package sheridan.climense.kmmsharedmodule.domain.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-13
 */
data class Routine(val id : Long, val title : String, val date : Long, val routineItems : List<Practice>, val progressItems : List<Practice>)