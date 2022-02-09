package sheridan.climense.scales_app2.models

import androidx.databinding.BaseObservable
import sheridan.climense.kmmsharedmodule.model.Roots
import sheridan.climense.kmmsharedmodule.model.Scale
import sheridan.climense.kmmsharedmodule.model.Tech
import sheridan.climense.kmmsharedmodule.model.TechTypes

class RoutineInputs : BaseObservable() {

    //data class scale (val scale : String, var isUsed : Boolean, val isFour : Boolean)
    //data class roots (val scale : String, var isUsed : Boolean)
    //data class tech (val scale : TechTypes, var isUsed : Boolean)

    companion object{

         val scaleOptions = arrayOf(
             Scale("Major", false, true),
             Scale("Har. minor", false, true),
             Scale("Mel. minor", false, true),
             Scale("Dim", false, true),
             Scale("Maj7", false, false),
             Scale("min7", false, false),
             Scale("Dom7", false, false),
             Scale("Aug", false, true)
        )

         val RootOptions = arrayOf(
             Roots("C", true),
             Roots("C#", true),
             Roots("D", true),
             Roots("D#", true),
             Roots("E", true),
             Roots("F", true),
             Roots("F#", true),
             Roots("G", true),
             Roots("G#", true),
             Roots("A", true),
             Roots("A#", true),
             Roots("B", true)
        )

        val CustRootOptions = arrayOf(
            Roots("C", false),
            Roots("C#", false),
            Roots("D", false),
            Roots("D#", false),
            Roots("E", false),
            Roots("F", false),
            Roots("F#", false),
            Roots("G", false),
            Roots("G#", false),
            Roots("A", false),
            Roots("A#", false),
            Roots("B", false)
        )



         val techOptions = arrayOf(
             Tech(TechTypes.Scale, false),
             Tech(TechTypes.Arp, false),
             Tech(TechTypes.Solid, false),
             Tech(TechTypes.Broken, false),
             Tech(TechTypes.Oct, false),
             Tech(TechTypes.CM, false),
        )
    }
}