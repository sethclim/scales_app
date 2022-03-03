package sheridan.climense.kmmsharedmodule.domain.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */

val scaleOptions : Map<ScaleType, Scale> = mapOf(
    ScaleType.Maj to Scale("Major", isFour = true),
    ScaleType.Min to Scale("Har. minor",  isFour = true),
    ScaleType.MelMin to Scale("Mel. minor", isFour = true),
    ScaleType.Dim to Scale("Dim",  isFour = true),
    ScaleType.Maj7 to Scale("Maj7",  isFour =false),
    ScaleType.Min7 to Scale("min7",  isFour = false),
    ScaleType.Dom7 to Scale("Dom7", isFour = false),
    ScaleType.Aug to Scale("Aug", isFour = true)
)