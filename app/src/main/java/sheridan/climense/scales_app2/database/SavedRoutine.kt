package sheridan.climense.scales_app2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import sheridan.climense.scales_app2.model.RoutineGenerator
import java.util.*

@Entity(tableName = "SavedRoutine")
@TypeConverters(Converters::class)
data class SavedRoutine (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "Key")
        val key : Long,

        @ColumnInfo(name = "Title")
        val title : String,

        @ColumnInfo(name = "Routine")
        val routine : Array<RoutineGenerator.Companion.practice>,

        @ColumnInfo(name = "inProgress")
        val inProgress : Array<RoutineGenerator.Companion.practice>?,

        @ColumnInfo(name = "Progress")
        val progress : Int,

        @ColumnInfo(name = "Total")
        val total : Int,

        @ColumnInfo(name = "Date")
        val date : Date,

        ) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as SavedRoutine

                if (key != other.key) return false

                return true
        }

        override fun hashCode(): Int {
                return key.hashCode()
        }
}