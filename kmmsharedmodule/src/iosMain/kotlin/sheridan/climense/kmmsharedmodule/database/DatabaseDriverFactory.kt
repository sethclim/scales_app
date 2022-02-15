package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.db.SqlDriver

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-15
 */
class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}