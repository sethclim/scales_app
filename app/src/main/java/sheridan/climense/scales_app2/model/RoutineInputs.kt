package sheridan.climense.scales_app2.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */
class RoutineInputs {
    companion object{

        var maj : Boolean = false
        var min : Boolean = false
        var dim : Boolean = false
        var aug : Boolean = false
        var maj7 : Boolean = false
        var dom7 : Boolean = false
        var min7 : Boolean = false
        var scales : Boolean = false
        var arps : Boolean = false
        var solid : Boolean = false
        var broken : Boolean = false
        var octaves : Boolean = false
        var contrary : Boolean = false


        var listOfScales = mutableListOf<String>()
        var listOftechniques = mutableListOf<String>()

        fun getScales(): MutableList<String>{
            if(maj)
                listOfScales.add("Major")
            else{
                listOfScales.remove("Major")
            }
            if(min)
                listOfScales.add("Minor")
            else{
                listOfScales.remove("Minor")
            }
            if(dim)
                listOfScales.add("Dim")
            else{
                listOfScales.remove("Dim")
            }
            if(aug)
                listOfScales.add("Aug")
            else{
                listOfScales.remove("Aug")
            }
            if(maj7)
                listOfScales.add("Maj 7")
            else{
                listOfScales.remove("Maj 7")
            }
            if(min7)
                listOfScales.add("min 7")
            else{
                listOfScales.remove("min 7")
            }
            if(dom7)
                listOfScales.add("Dom 7")
            else{
                listOfScales.remove("Dom 7")
            }

            return listOfScales
        }
        fun getTechnique(): MutableList<String>{
            if(scales)
                listOftechniques.add("Scales")
            else{
                listOftechniques.remove("Scales")
            }
            if(arps)
                listOftechniques.add("Arp")
            else{
                listOftechniques.remove("Arp")
            }
            if(scales)
                listOftechniques.add("Scales")
            else{
                listOftechniques.remove("Scales")
            }
            if(solid)
                listOftechniques.add("Solid Chords")
            else{
                listOftechniques.remove("Solid Chords")
            }
            if(broken)
                listOftechniques.add("Broken Chords")
            else{
                listOftechniques.remove("Broken Chords")
            }
            if(octaves)
                listOftechniques.add("Octaves")
            else{
                listOftechniques.remove("Octaves")
            }
            if(contrary)
                listOftechniques.add("Con Motion")
            else{
                listOftechniques.remove("Con Motion")
            }
            return listOftechniques
        }
    }
}