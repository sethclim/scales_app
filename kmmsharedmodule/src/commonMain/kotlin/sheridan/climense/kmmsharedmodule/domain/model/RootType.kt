package sheridan.climense.kmmsharedmodule.domain.model

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */
enum class RootType(val strName: String) {
    C(strName = "C"),
    Cs(strName = "C#"),
    D(strName = "D"),
    Ds(strName = "D#"),
    E(strName = "E"),
    F(strName = "F"),
    Fs(strName = "F#"),
    G(strName = "G"),
    Gs(strName = "G#"),
    A(strName = "A"),
    As(strName = "A#"),
    B(strName = "B");

    companion object {
        fun from(s: String): RootType? = RootType.values().find { it.strName == s }
    }
}