package sheridan.climense.kmmsharedmodule.domain.model.types

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */
enum class ScaleType(val strName: String) {
    Maj(strName = "Maj"),
    Min(strName = "Har. Min"),
    MelMin(strName = "Mel. Min"),
    Dim(strName = "Dim"),
    Maj7(strName = "Maj7"),
    Min7(strName = "Min7"),
    Dom7(strName = "Dom7"),
    Aug(strName = "Aug")
}
