package sheridan.climense.scales_app2.models

import androidx.room.ColumnInfo
import sheridan.climense.kmmsharedmodule.domain.model.TechTypes

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-02
 */
data class PracticeSave (@ColumnInfo(name = "Root") val root : String, @ColumnInfo(name = "Scale") val scale : String, @ColumnInfo(name = "Tech") val tech : TechTypes)
