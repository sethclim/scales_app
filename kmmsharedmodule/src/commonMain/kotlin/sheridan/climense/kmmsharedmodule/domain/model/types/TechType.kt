package sheridan.climense.kmmsharedmodule.domain.model.types

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-02
 */

enum class TechType(val strName: String){
    Scale("Scale"), Arp("Arp"), Solid("Solid"), Broken("Broken"),Oct("Oct"), CM("C.M.");

    companion object {
        fun from(s: String): TechType? = values().find { it.strName == s }
    }
}