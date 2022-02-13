package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.Practice

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
interface ICacheData {
    fun getAllPractice(): List<Practice>
}