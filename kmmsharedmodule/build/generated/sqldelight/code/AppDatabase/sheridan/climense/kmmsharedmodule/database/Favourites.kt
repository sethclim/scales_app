package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.ColumnAdapter
import kotlin.Long
import kotlin.String
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

public data class Favourites(
  public val key: Long,
  public val root: RootType,
  public val scale: String,
  public val tech: TechType
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
    public val rootAdapter: ColumnAdapter<RootType, String>,
    public val techAdapter: ColumnAdapter<TechType, String>
  )
}
