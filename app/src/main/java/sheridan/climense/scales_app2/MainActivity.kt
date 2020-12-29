package sheridan.climense.scales_app2

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // make the navigation work with the toolbar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_routine -> {
                    navController.navigate(R.id.routineCreator)
                    true
                }
                R.id.nav_saved -> {
                    navController.navigate(R.id.savedRoutinesPage)
                    true
                }
                R.id.nav_history -> {
                    navController.navigate(R.id.practiceHistoryPage)
                    true
                }
                else -> false
            }
        }
    }
}