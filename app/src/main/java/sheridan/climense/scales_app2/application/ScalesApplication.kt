package sheridan.climense.scales_app2.application

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import sheridan.climense.kmmsharedmodule.di.initKoin
import sheridan.climense.scales_app2.di.viewModelModule

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */
class ScalesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("ScaleApp", "Starting...")
        initKoin {
            androidContext(this@ScalesApplication)
            modules(
                viewModelModule
            )
        }
    }
}