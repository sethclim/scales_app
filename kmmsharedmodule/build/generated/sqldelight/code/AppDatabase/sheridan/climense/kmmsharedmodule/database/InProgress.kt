package sheridan.climense.kmmsharedmodule.database

import kotlin.Long
import kotlin.String

public data class InProgress(
  public val id: Long,
  public val practice: String
) {
  public override fun toString(): String = """
  |InProgress [
  |  id: $id
  |  practice: $practice
  |]
  """.trimMargin()
}
