package sheridan.climense.scales_app2.model

import androidx.databinding.BaseObservable

class RoutineInputs : BaseObservable() {

    companion object{

        data class scale (val scale : String, var isUsed : Boolean, val isFour : Boolean)
        data class roots (val scale : String, var isUsed : Boolean)
        data class tech (val scale : TechTypes, var isUsed : Boolean)

         val scaleOptions = arrayOf(
             scale("Major", false, true),
             scale("Har. minor", false, true),
             scale("Mel. minor", false, true),
             scale("Dim", false, true),
             scale("Maj7", false, false),
             scale("min7", false, false),
             scale("Dom7", false, false),
             scale("Aug", false, true)
        )

         val RootOptions = arrayOf(
             roots("C", true),
             roots("C#", true),
             roots("D", true),
             roots("D#", true),
             roots("E", true),
             roots("F", true),
             roots("F#", true),
             roots("G", true),
             roots("G#", true),
             roots("A", true),
             roots("A#", true),
             roots("B", true)
        )

        val CustRootOptions = arrayOf(
            roots("C", false),
            roots("C#", false),
            roots("D", false),
            roots("D#", false),
            roots("E", false),
            roots("F", false),
            roots("F#", false),
            roots("G", false),
            roots("G#", false),
            roots("A", false),
            roots("A#", false),
            roots("B", false)
        )

        enum class TechTypes(val strName: String){
            Scale("Scale"), Arp("Arp"), Solid("Solid"), Broken("Broken"),Oct("Oct"), CM("C.M.");

            companion object {
                fun from(s: String): TechTypes? = values().find { it.strName == s }
            }
        }

         val techOptions = arrayOf(
             tech(TechTypes.Scale, false),
             tech(TechTypes.Arp, false),
             tech(TechTypes.Solid, false),
             tech(TechTypes.Broken, false),
             tech(TechTypes.Oct, false),
             tech(TechTypes.CM, false),
        )
    }
}