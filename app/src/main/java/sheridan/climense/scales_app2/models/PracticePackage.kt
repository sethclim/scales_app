package sheridan.climense.scales_app2.models

import java.io.Serializable
import java.util.*

data class PracticePackage (
        val routine_name : String,
        val practice_array : Array<PracticeSave>,
        val savedPractice : Boolean,
        val key : Long = -1,
        val total : Int = 0,
        val date : Date = Date(1)
) : Serializable {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as PracticePackage

                if (key != other.key) return false

                return true
        }

        override fun hashCode(): Int {
                return key.hashCode()
        }
}