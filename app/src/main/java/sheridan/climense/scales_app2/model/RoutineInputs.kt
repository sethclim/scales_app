package sheridan.climense.scales_app2.model

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import sheridan.climense.scales_app2.BR

class RoutineInputs : BaseObservable() {

    companion object{

        data class scale (val scale : String, var isUsed : Boolean, val isFour : Boolean)
        data class roots (val scale : String, var isUsed : Boolean)
        data class tech (val scale : String, var isUsed : Boolean)

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

         val techOptions = arrayOf(
             tech("Scale", false),
             tech("Arp", false),
             tech("Solid", false),
             tech("Broken", false),
             tech("Oct", false),
             tech("C.M.", false),
        )
    }
}