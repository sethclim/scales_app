package sheridan.climense.kmmsharedmodule.features.creator
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.model.*
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.interactors.AddRoutineToRoutinesUseCase

class CreatorViewModel: BaseViewModel<CreatorContract.Event, CreatorContract.State, UiEffect>() {

    private val addRoutineToRoutinesUseCase: AddRoutineToRoutinesUseCase by inject()
    private val generator: RoutineGenerator by inject()

    var routine : Array<Practice> = arrayOf()


    override fun createInitialState(): CreatorContract.State =
        CreatorContract.State(
            scalesCheckBoxes = BasicUiState.Idle,
        )

    override fun handleEvent(event: CreatorContract.Event) {
        when (event) {
            CreatorContract.Event.AddMaj -> manageScaleTypes(true, ScaleType.Maj)
            CreatorContract.Event.RemoveMaj -> manageScaleTypes(false, ScaleType.Maj)
            CreatorContract.Event.AddMin -> manageScaleTypes(true, ScaleType.Min)
            CreatorContract.Event.RemoveMin -> manageScaleTypes(false, ScaleType.Maj)
            CreatorContract.Event.AddMelMin -> manageScaleTypes(true, ScaleType.MelMin)
            CreatorContract.Event.RemoveMelMin -> manageScaleTypes(false, ScaleType.MelMin)
            CreatorContract.Event.AddDim -> manageScaleTypes(true, ScaleType.Dim)
            CreatorContract.Event.RemoveDim -> manageScaleTypes(false, ScaleType.Dim)
            CreatorContract.Event.AddMaj7 -> manageScaleTypes(true, ScaleType.Maj7)
            CreatorContract.Event.RemoveMaj7 -> manageScaleTypes(false, ScaleType.Maj7)
            CreatorContract.Event.AddMin7 -> manageScaleTypes(true, ScaleType.Min7)
            CreatorContract.Event.RemoveMin7 -> manageScaleTypes(false, ScaleType.Min7)
            CreatorContract.Event.AddDom7 -> manageScaleTypes(true, ScaleType.Dom7)
            CreatorContract.Event.RemoveDom7 -> manageScaleTypes(false, ScaleType.Dom7)
            CreatorContract.Event.AddAug -> manageScaleTypes(true, ScaleType.Aug)
            CreatorContract.Event.RemoveAug -> manageScaleTypes(false, ScaleType.Aug)
        }
    }


    fun generateRoutine(roots1 : Array<Roots>, scales1 : Array<Scale>, techs1 : Array<Tech>) : Boolean{

        val roots : MutableList<Roots> = mutableListOf()
        val scales : MutableList<Scale> = mutableListOf()
        val techs : MutableList<Tech> = mutableListOf()

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()

        scaleCbMap?.entries?.filter { cb -> cb.value }

        if (scaleCbMap != null) {
            for (entry in scaleCbMap)
            {
                scaleOptions[entry.key]?.let { scales.add(it) }
            }
        }

        routine =  generator.generate(roots.toTypedArray(), scales.toTypedArray(), techs.toTypedArray())

        return routine.isNotEmpty()
    }

//    fun createFavRoutine() : Boolean{
//        viewModelScope.launch {
//            val pr = practiceDao.getNLFavourites()
//            RoutineGenerator.favourites =  Practice(pr.root, pr.scale, pr.tech)
//        }
//         routine =  RoutineGenerator.generate()
//
//        return routine.isNotEmpty()
//    }

    fun saveRoutine(name : String, date : Long){
        //generateRoutine()
        //val savedRoutine = Routine(0L, name, routine,null, 0,routine.size, date)
        val savedRoutine = Routine(0L, name, date)
        addRoutineToRoutinesUseCase.execute(savedRoutine)
    }

    private fun manageScaleTypes(addRemove : Boolean, scaleType : ScaleType){

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()

        if(scaleCbMap != null){
            scaleCbMap[scaleType] = addRemove
            setState { copy(scalesCheckBoxes = BasicUiState.Success(scaleCbMap)) }
        }
    }
}