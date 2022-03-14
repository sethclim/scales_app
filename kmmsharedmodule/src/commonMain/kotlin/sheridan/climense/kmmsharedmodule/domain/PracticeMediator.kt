package sheridan.climense.kmmsharedmodule.domain

import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.*
import sheridan.climense.kmmsharedmodule.domain.model.PracticeContainer

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain
created by: seth
on: 2022-03-09           */

class PracticeMediator {

    val list : List<PracticeContainer> = emptyList()
    private var _practiceListFlow = MutableStateFlow(list)
    val practiceListFlow : StateFlow<List<PracticeContainer>> get() = _practiceListFlow.asStateFlow()

    fun setPracticeList(routine : List<PracticeContainer>)
    {
        if(routine.isNotEmpty())
        {
            _practiceListFlow.update { routine }
        }
        else{
            throw Exception("List can't be empty")
        }
    }

    fun clearList()
    {
        _practiceListFlow.update { emptyList() }
    }
}