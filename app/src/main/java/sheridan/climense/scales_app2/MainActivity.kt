package sheridan.climense.scales_app2

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_routine -> {
                    navController.navigate(R.id.routineCreator)
                    true
                }
                R.id.nav_practice -> {
                    navController.navigate(R.id.practicePage)
                    true
                }
                else -> false
            }
        }
    }
}