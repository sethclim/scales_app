package sheridan.climense.kmmsharedmodule.base.mvi

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
sealed class BasicUiState<out T> {
    data class Success<T>(val data: T) : BasicUiState<T>()
    object Idle : BasicUiState<Nothing>()
    object Loading : BasicUiState<Nothing>()
    object Empty : BasicUiState<Nothing>()
    data class Error(val message: String? = null) : BasicUiState<Nothing>()

    fun accessData() : T?{
        return when (this) {
            is Success -> data
            else -> null
        }
    }
}