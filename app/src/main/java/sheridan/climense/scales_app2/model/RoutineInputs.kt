package sheridan.climense.scales_app2.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */
class RoutineInputs {
    companion object{

        var scales : Boolean = false

        var listOfScales = mutableListOf<String>()

        fun getScales(): MutableList<String>{
            if(scales)
                listOfScales.add("Scales")
            else{
                listOfScales.remove("Scales")
            }


            return listOfScales
        }
        fun getTechnique(){
            //ToDo
        }





    }
}