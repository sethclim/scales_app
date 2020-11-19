package sheridan.climense.scales_app2.util

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DateConverters {
    companion object{

        private val dateFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy")

        fun formatDate(date: Date?): String? {
            return date?.toInstant()
                    ?.atZone(ZoneId.systemDefault())
                    ?.toLocalDate()
                    ?.format(dateFormatter)
        }

        object Converters {
            @TypeConverter
            fun fromTimestamp(value: Long?): Date? {
                return value?.let { Date(it) }
            }

            @TypeConverter
            fun dateToTimestamp(date: Date?): Long? {
                return if (date == null) null else date.getTime()
            }
        }
    }
}