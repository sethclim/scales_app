package sheridan.climense.scales_app2.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import sheridan.climense.scales_app2.databinding.SettingsFragmentBinding

class SettingsPage : Fragment() {

    lateinit var binding : SettingsFragmentBinding
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{

        binding = SettingsFragmentBinding.inflate(inflater, container, false)

        val sharedPreferences : SharedPreferences = requireActivity().getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.dayNightSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putInt("theme", 1)
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putInt("theme", 0)
            }
            editor.apply()
        }

        binding.dayNightSwitch.isChecked = sharedPreferences.getInt("theme", 0) == 1

        return binding.root
    }
}