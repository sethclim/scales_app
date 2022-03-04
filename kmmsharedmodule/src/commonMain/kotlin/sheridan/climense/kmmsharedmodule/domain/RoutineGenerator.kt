package sheridan.climense.kmmsharedmodule.domain


import sheridan.climense.kmmsharedmodule.domain.model.*
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

class RoutineGenerator {
        var favourites : Array<Practice> = arrayOf()
        var routine : MutableList<Practice> = mutableListOf()

        fun generate(roots : Array<RootType>, scales : Array<Scale>, techs : Array<TechType>) : Array<Practice>{

            for(root in roots){
                for(scale in scales){
                    if(scale.isFour){
                        for(tech in techs){
                            val temp = Practice(0L,root, scale.name, tech)
                            routine.add(temp)
                        }
                    }
                    else{
                        for(tech in techs){
                            val temp = Practice(0L, root, scale.name, tech)
                            routine.add(temp)
                        }
                    }
                }
            }
            return routine.toTypedArray()
        }
}