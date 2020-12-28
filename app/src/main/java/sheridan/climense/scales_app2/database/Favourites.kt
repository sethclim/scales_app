package sheridan.climense.scales_app2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourites")
data class Favourites (

    @PrimaryKey
    @ColumnInfo(name = "Key")
    val key : String,

    @ColumnInfo(name = "Root")
    val root : String,

    @ColumnInfo(name = "Scale")
    val scale : String,

    @ColumnInfo(name = "Tech")
    val tech : String,

    @ColumnInfo(name = "Fav")
    val fav : Boolean,

)