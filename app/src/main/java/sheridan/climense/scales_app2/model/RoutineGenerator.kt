package sheridan.climense.scales_app2.model

import androidx.room.ColumnInfo

class RoutineGenerator {
    companion object{

        data class practice (@ColumnInfo(name = "Root") val root : String, @ColumnInfo(name = "Scale") val scale : String, @ColumnInfo(name = "Tech") val tech : RoutineInputs.Companion.TechTypes)

        var favourites : Array<practice> = arrayOf()
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
                                    routine.add(temp)
                                }

                            }
                        }
                        else if(scale.isUsed ){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = practice(root.scale, scale.scale, tech.scale)
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