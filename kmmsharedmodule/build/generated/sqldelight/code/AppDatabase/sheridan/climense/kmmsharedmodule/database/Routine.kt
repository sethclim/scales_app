package sheridan.climense.kmmsharedmodule.database

import kotlin.Long
import kotlin.String

public data class Routine(
  public val id: Long,
  public val practice: String
) {
  public override fun toString(): String = """
  |Routine [
  |  id: $id
  |  practice: $practice
  |]
  """.trimMargin()
}
