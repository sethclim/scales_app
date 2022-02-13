package sheridan.climense.kmmsharedmodule.database

import kotlin.Long
import kotlin.String

public data class PracticeRecord(
  public val date: Long,
  public val scales: Long,
  public val arps: Long,
  public val oct: Long,
  public val solid: Long,
  public val broken: Long,
  public val conMotion: Long
) {
  public override fun toString(): String = """
  |PracticeRecord [
  |  date: $date
  |  scales: $scales
  |  arps: $arps
  |  oct: $oct
  |  solid: $solid
  |  broken: $broken
  |  conMotion: $conMotion
  |]
  """.trimMargin()
}
