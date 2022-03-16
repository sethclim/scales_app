package sheridan.climense.kmmsharedmodule.domain.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-13
 */
data class PracticeSession(
    var date: Long,
    val scale: Long,
    val arps: Long,
    val oct : Long,
    val solid: Long,
    val broken: Long,
    val conMotion: Long )
