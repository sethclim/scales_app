package sheridan.climense.scales_app2.database

import androidx.room.*

@Entity(tableName = "PracticeRecord")
data class PracticeRecord (

        @ColumnInfo(name = "Scales")
        val scales: Int,

        @ColumnInfo(name = "Arps")
        val arps: Int,

        @ColumnInfo(name = "Oct")
        val oct: Int,

        @ColumnInfo(name = "Solid")
        val solid: Int,

        @ColumnInfo(name = "Broken")
        val broken: Int,

        @ColumnInfo(name = "ConMotion")
        val conMotion: Int,

        @PrimaryKey
        @ColumnInfo(name = "date")
        val date: String,

        )