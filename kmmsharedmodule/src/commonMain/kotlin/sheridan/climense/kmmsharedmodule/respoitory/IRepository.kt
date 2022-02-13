package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.Practice

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
interface IRepository {
    fun getPractice(): List<Practice>
//    fun getCharactersFavorites(): Flow<List<Character>>
//    fun getCharacter(id: Int): Flow<Character>
//    fun addCharacterToFavorites(character: Character): Flow<Unit>
//    fun removeCharacterFromFavorite(idCharacter: Int): Flow<Unit>
//    fun isCharacterFavorite(idCharacter: Int): Flow<Boolean>
}