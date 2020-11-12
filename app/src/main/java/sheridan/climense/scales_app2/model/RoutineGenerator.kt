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

        var routine : MutableList<String> = mutableListOf()

        fun generate() : Array<String>{
            val roots = RoutineInputs.RootOptions
            val scales = RoutineInputs.scaleOptions
            val techs = RoutineInputs.techOptions

            for(root in roots){

                if(root.isUsed){
                    for(scale in scales){
                        if(scale.isUsed  && scale.isFour){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = "${root.scale} ${scale.scale} ${tech.scale}"
                                    routine.add(temp)
                                }

                            }
                        }
                        else if(scale.isUsed ){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = "${root.scale} ${scale.scale} ${tech.scale}"
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