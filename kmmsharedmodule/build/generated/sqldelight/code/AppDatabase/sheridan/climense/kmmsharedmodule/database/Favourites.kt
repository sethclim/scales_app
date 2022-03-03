package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.ColumnAdapter
import kotlin.Long
import kotlin.String
import sheridan.climense.kmmsharedmodule.domain.model.TechTypes

public data class Favourites(
  public val key: Long,
  public val root: String,
  public val scale: String,
  public val tech: TechTypes
) {
  public override fun toString(): String = """
  |Favourites [
  |  key: $key
  |  root: $root
  |  scale: $scale
  |  tech: $tech
  |]
  """.trimMargin()

  public class Adapter(
    public val techAdapter: ColumnAdapter<TechTypes, String>
  )
}
