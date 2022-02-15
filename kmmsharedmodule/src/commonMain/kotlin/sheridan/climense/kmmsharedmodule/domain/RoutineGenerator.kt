package sheridan.climense.kmmsharedmodule.domain


import sheridan.climense.kmmsharedmodule.model.*

class RoutineGenerator {
    companion object{


        var favourites : Array<Practice> = arrayOf()
        var routine : MutableList<Practice> = mutableListOf()


        fun generate(roots : Array<Roots>, scales : Array<Scale>, techs : Array<Tech>) : Array<Practice>{

            for(root in roots){

                if(root.isUsed){
                    for(scale in scales){
                        if(scale.isUsed  && scale.isFour){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = Practice(0L,root.scale, scale.scale, tech.scale)
                                    routine.add(temp)
                                }

                            }
                        }
                        else if(scale.isUsed ){
                            for(tech in techs){
                                if(tech.isUsed){
                                    val temp = Practice(0L, root.scale, scale.scale, tech.scale)
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