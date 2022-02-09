package sheridan.climense.scales_app2.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.model.TechTypes
import sheridan.climense.scales_app2.models.PracticeSave
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import sheridan.climense.scales_app2.models.RoutineInputs


class Converters {

    private val dateFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy")

    fun formatDate(date: Date?): String? {
        return date?.toInstant()
                ?.atZone(ZoneId.systemDefault())
                ?.toLocalDate()
                ?.format(dateFormatter)
    }

    @TypeConverter
    fun toLong(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(timestamp: Long): LocalDate? {
        return LocalDate.ofEpochDay(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun fromString(value: String?): Array<PracticeSave?>? {
        val arrayType: Type = object : TypeToken<Array<PracticeSave?>?>() {}.type
        if(value != null){
            return Gson().fromJson(value, arrayType)
        }
        else{
            return null
        }

    }

    @TypeConverter
    fun fromArray(array: Array<PracticeSave>?): String? {
        val gson = Gson()
        Log.d("JSON??", gson.toJson(array))
        if(array != null)
            return gson.toJson(array)
        else{
            return null
        }
    }

    @TypeConverter
    fun fromTechTypeToString(techtype: TechTypes?): String? {
        return techtype?.strName
    }
    @TypeConverter
    fun fromStringToTechType(techtype: String?): TechTypes? {
        return if (techtype.isNullOrEmpty()) null else TechTypes.from(techtype)
    }
}