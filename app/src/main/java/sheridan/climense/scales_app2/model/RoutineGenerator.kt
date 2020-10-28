package sheridan.climense.scales_app2.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */
class RoutineGenerator {
    companion object{

        var routine : Array<String> = arrayOf()


        fun generate(scales : MutableList<String>){

            routine = scales.toTypedArray()


        }


    }
}