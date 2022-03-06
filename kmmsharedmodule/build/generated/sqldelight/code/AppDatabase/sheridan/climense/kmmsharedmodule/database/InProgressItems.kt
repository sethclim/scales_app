package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.ColumnAdapter
import kotlin.Long
import kotlin.String
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

public data class InProgressItems(
  public val id: Long,
  public val routineref: Long,
  public val root: RootType,
  public val scale: ScaleType,
  public val tech: TechType
) {
  public override fun toString(): String = """
  |InProgressItems [
  |  id: $id
  |  routineref: $routineref
  |  root: $root
  |  scale: $scale
  |  tech: $tech
  |]
  """.trimMargin()

  public class Adapter(
    public val rootAdapter: ColumnAdapter<RootType, String>,
    public val scaleAdapter: ColumnAdapter<ScaleType, String>,
    public val techAdapter: ColumnAdapter<TechType, String>
  )
}
