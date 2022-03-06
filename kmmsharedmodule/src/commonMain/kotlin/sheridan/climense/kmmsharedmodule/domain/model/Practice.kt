package sheridan.climense.kmmsharedmodule.domain.model

import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-07
 */
data class Practice (val id : Long, val root : RootType, val scale : ScaleType, val tech : TechType)
