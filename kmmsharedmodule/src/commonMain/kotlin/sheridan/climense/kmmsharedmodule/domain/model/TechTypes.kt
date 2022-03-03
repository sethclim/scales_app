package sheridan.climense.kmmsharedmodule.domain.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-02
 */

enum class TechTypes(val strName: String){
    Scale("Scale"), Arp("Arp"), Solid("Solid"), Broken("Broken"),Oct("Oct"), CM("C.M.");

    companion object {
        fun from(s: String): TechTypes? = values().find { it.strName == s }
    }
}