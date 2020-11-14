package sheridan.climense.scales_app2.model

import android.util.Log

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2020-10-28
 */
class RoutineGenerator {
    companion object{

        data class practice (val root : String, val scale : String, val tech : String )

        var routine : MutableList<practice> = mutableListOf()

        var roots = RoutineInputs.RootOptions
        val scales = RoutineInputs.scaleOptions
        val techs = RoutineInputs.techOptions

        fun generate() : Array<practice>{


            for(root in roots){

                if(root.isUsed){
                    for(scale in scales){
                        if(scale.isUsed  && scale.isFour){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = practice(root.scale, scale.scale, tech.scale)
                                    //val temp = "${root.scale} ${scale.scale} ${tech.scale}"
                                    routine.add(temp)
                                }

                            }
                        }
                        else if(scale.isUsed ){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = practice(root.scale, scale.scale, tech.scale)
                                    //val temp = "${root.scale} ${scale.scale} ${tech.scale}"
                                    routine.add(temp)
                                }
                            }
                        }
                    }
                }
            }
            return routine.toTypedArray()
        }
    }
}