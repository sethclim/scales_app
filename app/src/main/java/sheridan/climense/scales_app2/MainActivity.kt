package sheridan.climense.scales_app2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Debug
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import sheridan.climense.kmmsharedmodule.Greeting
import sheridan.climense.kmmsharedmodule.database.DatabaseDriverFactory
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sharedPreferences : SharedPreferences = getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
        if(!sharedPreferences.contains("theme")){
            val editor = sharedPreferences.edit()
            editor.putInt("theme", 0)
            editor.apply()
        }
        val theme = sharedPreferences.getInt("theme", 1)
        if (theme == 1) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


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

        Greeting().insert(DatabaseDriverFactory(this), PracticeSession(2,10,2,2,2,2,10))

        val data : List<PracticeSession> = Greeting().getData(DatabaseDriverFactory(this))

        Log.d("TEst Data", data[0].scale.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host_fragment)) ||
                when (item.itemId) {
                    R.id.action_settings -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_settingsPage)
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
    }

}