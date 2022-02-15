package sheridan.climense.kmmsharedmodule.database

import kotlin.Long
import kotlin.String

public data class Routines(
  public val id: Long,
  public val title: String,
  public val date: Long
) {
  public override fun toString(): String = """
  |Routines [
  |  id: $id
  |  title: $title
  |  date: $date
  |]
  """.trimMargin()
}
