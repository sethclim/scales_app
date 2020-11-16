package sheridan.climense.scales_app2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PracticeRecord")
data class PracticeRecord (
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "Scales")
    val scales: Int,

    @ColumnInfo(name = "Arps")
    val arps: Int,

    @ColumnInfo(name = "Oct")
    val oct: Int,

    @ColumnInfo(name = "Solid")
    val solid: Int,


)