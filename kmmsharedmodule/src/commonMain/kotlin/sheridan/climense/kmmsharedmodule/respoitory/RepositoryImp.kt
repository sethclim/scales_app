package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.Practice

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class RepositoryImp(private val cacheData: ICacheData,) : IRepository {
    override fun getPractice(): List<Practice> { return cacheData.getAllPractice() }
}