package sheridan.climense.scales_app2.database

import androidx.room.TypeConverter
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class Converters {

    private val dateFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy")

    fun formatDate(date: Date?): String? {
        return date?.toInstant()
                ?.atZone(ZoneId.systemDefault())
                ?.toLocalDate()
                ?.format(dateFormatter)
    }

//    @TypeConverter
//    fun fromStringToDate(value: Long?): String? {
//
//    }

    @TypeConverter
    fun fromDateToString(date: Date?): String {
        var strDate : String? = ""
        if(date != null)
            strDate = formatDate(date)
        else{
            strDate = "empty"
        }
        return strDate!!
    }
}