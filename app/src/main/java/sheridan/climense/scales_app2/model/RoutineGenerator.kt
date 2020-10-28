package sheridan.climense.scales_app2.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */
class RoutineGenerator {
    companion object{

        var routine : MutableList<String> = mutableListOf()


        fun generate(scales : MutableList<String>, techniques : MutableList<String>) : Array<String>{

            for(item in scales){
                for(tech in techniques){
                    val oneItem = "${item} + ${tech}"
                    routine.add(oneItem)
                }
            }
            return routine.toTypedArray()
        }


    }
}