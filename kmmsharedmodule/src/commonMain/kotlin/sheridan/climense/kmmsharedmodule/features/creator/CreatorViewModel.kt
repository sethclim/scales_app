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

    private var roots = mutableMapOf(
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
    )

    override fun createInitialState(): CreatorContract.State =
        CreatorContract.State(
            scalesCheckBoxes = BasicUiState.Success(mutableMapOf()),
            techCheckBoxes =  BasicUiState.Success(mutableMapOf()),
            rootCheckBoxes = BasicUiState.Success(mutableMapOf(
                RootType.C to false,
                RootType.Cs to false,
                RootType.D to false,
                RootType.Ds to false,
                RootType.E to false,
                RootType.F to false,
                RootType.Fs to false,
                RootType.G to false,
                RootType.Gs to false,
                RootType.A to false,
                RootType.As to false,
                RootType.B to false
            )),
            customRootState = BasicUiState.Success(data = false)
        )

    override fun handleEvent(event: CreatorContract.Event) {
        when (event) {
            is CreatorContract.Event.SetScaleEvent -> manageScaleTypes(event.scaleType, event.addRemove)
            is CreatorContract.Event.SetTechEvent -> manageTechTypes(event.techType, event.addRemove)

            is CreatorContract.Event.SaveRoutine -> saveRoutine(event.name, 0L)
            is CreatorContract.Event.SetRootsEvent -> updateRoots(event.rootType, event.addRemove)

            CreatorContract.Event.ConfirmRootsEvent -> setRoots(onOff = true)
            is CreatorContract.Event.ToggleCustomRoots -> setRoots(event.onOff)
        }
    }


    fun generateRoutine() : Boolean{

        var routine : Array<Practice> = emptyArray()
        val scales : MutableList<Scale> = mutableListOf()

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()
        val techCbMap = uiState.value.techCheckBoxes.accessData()


        scaleCbMap?.entries?.filter { cb -> cb.value }
        techCbMap?.entries?.filter { cb -> cb.value }
        roots.entries.filter { cb -> cb.value }

        Logger.i{"Sizes: ${scaleCbMap?.size} ${techCbMap?.size} ${roots?.size}"}

        if (scaleCbMap != null) {
            for (entry in scaleCbMap)
            {
                scaleOptions[entry.key]?.let { scales.add(it) }
            }
        }

        if (techCbMap != null) {
                routine =  generator.generate(roots.keys.toTypedArray(), scales.toTypedArray(), techCbMap.keys.toTypedArray())
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

    private fun saveRoutine(name : String, date : Long){
        generateRoutine()
        //val savedRoutine = Routine(0L, name, routine,null, 0,routine.size, date)
        val savedRoutine = Routine(0L, name, date)
        addRoutineToRoutinesUseCase.execute(savedRoutine)
    }

    private fun manageScaleTypes(scaleType : ScaleType, addRemove : Boolean){

        val scaleCbMap = uiState.value.scalesCheckBoxes.accessData()

        if(scaleCbMap != null){
            scaleCbMap[scaleType] = addRemove

            Logger.i {"${scaleCbMap.entries}"}

            setState { copy(scalesCheckBoxes = BasicUiState.Success(scaleCbMap)) }
        }
    }

    private fun manageTechTypes(techType: TechType, addRemove: Boolean){

        val techCbMap = uiState.value.techCheckBoxes.accessData()

        if(techCbMap != null){
            techCbMap[techType] = addRemove
            setState { copy(techCheckBoxes = BasicUiState.Success(techCbMap)) }
        }
    }

    private fun updateRoots(rootType: RootType, addRemove: Boolean){
        val rootCbMap = uiState.value.rootCheckBoxes.accessData()

        if(rootCbMap != null){
            rootCbMap[rootType] = addRemove
            setState { copy(rootCheckBoxes = BasicUiState.Success(rootCbMap)) }
        }
    }

    private fun setRoots(onOff : Boolean){
        if(onOff){
            roots = uiState.value.rootCheckBoxes.accessData()!!
        }
        else{
            roots = mutableMapOf(
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
            )
        }
        setState { copy(customRootState = BasicUiState.Success(onOff)) }
    }
}