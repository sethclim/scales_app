package sheridan.climense.kmmsharedmodule.features.creator
import co.touchlab.kermit.Logger
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.model.*
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.interactors.AddRoutineToRoutinesUseCase
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

class CreatorViewModel: BaseViewModel<CreatorContract.Event, CreatorContract.State, UiEffect>() {

    private val addRoutineToRoutinesUseCase: AddRoutineToRoutinesUseCase by inject()
    private val generator: RoutineGenerator by inject()

    override fun createInitialState(): CreatorContract.State =
        CreatorContract.State(
            scalesCheckBoxes = BasicUiState.Success(mutableMapOf()),
            techCheckBoxes =  BasicUiState.Success(mutableMapOf()),
            rootCheckBoxes = BasicUiState.Success(mutableMapOf(
                RootType.C to true,
                RootType.Cs to true,
                RootType.D to true,
                RootType.Ds to true,
                RootType.E to true,
                RootType.F to true,
                RootType.Fs to true,
                RootType.G to true,
                RootType.Gs to true,
                RootType.A to true,
                RootType.As to true,
                RootType.B to true
            ))
        )

    override fun handleEvent(event: CreatorContract.Event) {
        when (event) {
            CreatorContract.Event.AddMaj -> manageScaleTypes(addRemove = true, ScaleType.Maj)
            CreatorContract.Event.RemoveMaj -> manageScaleTypes(addRemove = false, ScaleType.Maj)
            CreatorContract.Event.AddMin -> manageScaleTypes(addRemove = true, ScaleType.Min)
            CreatorContract.Event.RemoveMin -> manageScaleTypes(addRemove = false, ScaleType.Maj)
            CreatorContract.Event.AddMelMin -> manageScaleTypes(addRemove = true, ScaleType.MelMin)
            CreatorContract.Event.RemoveMelMin -> manageScaleTypes(addRemove = false, ScaleType.MelMin)
            CreatorContract.Event.AddDim -> manageScaleTypes(addRemove = true, ScaleType.Dim)
            CreatorContract.Event.RemoveDim -> manageScaleTypes(addRemove = false, ScaleType.Dim)
            CreatorContract.Event.AddMaj7 -> manageScaleTypes(addRemove = true, ScaleType.Maj7)
            CreatorContract.Event.RemoveMaj7 -> manageScaleTypes(addRemove = false, ScaleType.Maj7)
            CreatorContract.Event.AddMin7 -> manageScaleTypes(addRemove = true, ScaleType.Min7)
            CreatorContract.Event.RemoveMin7 -> manageScaleTypes(addRemove = false, ScaleType.Min7)
            CreatorContract.Event.AddDom7 -> manageScaleTypes(addRemove = true, ScaleType.Dom7)
            CreatorContract.Event.RemoveDom7 -> manageScaleTypes(addRemove = false, ScaleType.Dom7)
            CreatorContract.Event.AddAug -> manageScaleTypes(addRemove = true, ScaleType.Aug)
            CreatorContract.Event.RemoveAug -> manageScaleTypes(addRemove = false, ScaleType.Aug)

            CreatorContract.Event.AddScales -> manageTechTypes(addRemove = true, TechType.Scale)
            CreatorContract.Event.AddArp -> manageTechTypes(addRemove = true, TechType.Arp)
            CreatorContract.Event.AddSolid -> manageTechTypes(addRemove = true, TechType.Solid)
            CreatorContract.Event.AddBroken -> manageTechTypes(addRemove = true, TechType.Broken)
            CreatorContract.Event.AddOct -> manageTechTypes(addRemove = true, TechType.Oct)
            CreatorContract.Event.AddConMotion -> manageTechTypes(addRemove = true, TechType.CM)
            CreatorContract.Event.RemoveScales -> manageTechTypes(addRemove = false, TechType.Scale)
            CreatorContract.Event.RemoveArp -> manageTechTypes(addRemove = false, TechType.Arp)
            CreatorContract.Event.RemoveSolid -> manageTechTypes(addRemove = false, TechType.Solid)
            CreatorContract.Event.RemoveBroken -> manageTechTypes(addRemove = false, TechType.Broken)
            CreatorContract.Event.RemoveOct -> manageTechTypes(addRemove = false, TechType.Oct)
            CreatorContract.Event.RemoveConMotion -> manageTechTypes(addRemove = false, TechType.CM)
        }
    }


    fun generateRoutine() : Boolean{

        var routine : Array<Practice> = emptyArray()
        val scales : MutableList<Scale> = mutableListOf()

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()
        val techCbMap = uiState.value.techCheckBoxes.accessData()
        val rootCbMap = uiState.value.rootCheckBoxes.accessData()

        scaleCbMap?.entries?.filter { cb -> cb.value }
        techCbMap?.entries?.filter { cb -> cb.value }
        rootCbMap?.entries?.filter { cb -> cb.value }

        Logger.i{"Sizes: ${scaleCbMap?.size} ${techCbMap?.size} ${rootCbMap?.size}"}

        if (scaleCbMap != null) {
            for (entry in scaleCbMap)
            {
                scaleOptions[entry.key]?.let { scales.add(it) }
            }
        }

        if (techCbMap != null && rootCbMap != null) {
                routine =  generator.generate(rootCbMap.keys.toTypedArray(), scales.toTypedArray(), techCbMap.keys.toTypedArray())
        }

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
        generateRoutine()
        //val savedRoutine = Routine(0L, name, routine,null, 0,routine.size, date)
        val savedRoutine = Routine(0L, name, date)
        addRoutineToRoutinesUseCase.execute(savedRoutine)
    }

    private fun manageScaleTypes(addRemove : Boolean, scaleType : ScaleType){

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()

        if(scaleCbMap != null){
            scaleCbMap[scaleType] = addRemove

            Logger.i {"${scaleCbMap.entries}"}

            setState { copy(scalesCheckBoxes = BasicUiState.Success(scaleCbMap)) }
        }
    }

    private fun manageTechTypes(addRemove : Boolean, techType: TechType){

        val techCbMap = uiState.value.techCheckBoxes.accessData()

        if(techCbMap != null){
            techCbMap[techType] = addRemove
            setState { copy(techCheckBoxes = BasicUiState.Success(techCbMap)) }
        }
    }
}