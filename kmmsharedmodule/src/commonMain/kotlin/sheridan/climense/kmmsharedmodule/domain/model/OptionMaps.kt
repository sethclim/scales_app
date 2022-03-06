package sheridan.climense.kmmsharedmodule.domain.model

import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */

val scaleOptions : Map<ScaleType, Scale> = mapOf(
    ScaleType.Maj to Scale(ScaleType.Maj, isFour = true),
    ScaleType.Min to Scale(ScaleType.Min,  isFour = true),
    ScaleType.MelMin to Scale(ScaleType.MelMin, isFour = true),
    ScaleType.Dim to Scale(ScaleType.Dim,  isFour = true),
    ScaleType.Maj7 to Scale(ScaleType.Maj7,  isFour =false),
    ScaleType.Min7 to Scale(ScaleType.Min7,  isFour = false),
    ScaleType.Dom7 to Scale(ScaleType.Dom7, isFour = false),
    ScaleType.Aug to Scale(ScaleType.Aug, isFour = true)
)