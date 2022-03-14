package sheridan.climense.kmmsharedmodule.domain


import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.executor.MainIoExecutor
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.model.*
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

class RoutineGenerator : MainIoExecutor() {
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase by inject()
    //make sure we're getting fav's here
    private var favourites : List<Practice> = emptyList()

    init {
        getCurrentFavourites()
    }

    fun generate(roots : Array<RootType>, scales : Array<Scale>, techs : Array<TechType>) : Array<PracticeContainer>{
        val routine : MutableList<PracticeContainer> = mutableListOf()
        //should just be a flowable so its always current and save time
        getCurrentFavourites()

        for(root in roots){
            for(scale in scales){
                if(scale.isFour){
                    for(tech in techs){
                        val temp = PracticeContainer(0L,root, scale.name, tech, favourites.containsMatch(root, scale, tech))
                        routine.add(temp)
                    }
                }
                else{
                    for(tech in techs){
                        val temp = PracticeContainer(0L, root, scale.name, tech, favourites.containsMatch(root, scale, tech))
                        routine.add(temp)
                    }
                }
            }
        }
        return routine.toTypedArray()
    }

    fun generate(practiceList : List<Practice>) : Array<PracticeContainer> {
        val routine : MutableList<PracticeContainer> = mutableListOf()
        for(practice in practiceList){
            routine.add(
                PracticeContainer(
                    practice.id,
                    practice.root,
                    practice.scale,
                    practice.tech,
                    favourites.containsMatch(practice.root, scaleOptions.get(practice.scale)!!, practice.tech)
                )
            )
        }
        return routine.toTypedArray()
    }

    private fun getCurrentFavourites(){
        launch(getAllFavouritesUseCase.execute(), { favourites ->
            this.favourites = favourites
        })
    }

    private fun List<Practice>.containsMatch(root : RootType, scale: Scale, techType: TechType) : Boolean {
        return this.any{ it.root == root && it.scale == scale.name && it.tech == techType }
    }
}

