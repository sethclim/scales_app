package sheridan.climense.scales_app2.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sheridan.climense.scales_app2.model.RoutineGenerator
import java.lang.reflect.Type
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

    @TypeConverter
    fun fromString(value: String?): Array<RoutineGenerator.Companion.practice?> {
        val arrayType: Type = object : TypeToken<Array<RoutineGenerator.Companion.practice?>?>() {}.type
        return Gson().fromJson(value, arrayType)
    }

    @TypeConverter
    fun fromArray(array: Array<RoutineGenerator.Companion.practice>): String {
        val gson = Gson()
        Log.d("JSON??", gson.toJson(array))
        return gson.toJson(array)
    }
}