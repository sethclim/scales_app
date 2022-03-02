package sheridan.climense.scales_app2.ui.practice


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import sheridan.climense.kmmsharedmodule.features.practice.PracticeViewModel

class PracticePage : Fragment() {

    //private val safeArgs: PracticePageArgs by navArgs()
    private val viewModel: PracticePageViewModel by viewModels()
    private lateinit var binding : PracticePageFragmentBinding

    private val practiceVM: PracticeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PracticePageFragmentBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
           viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
               Log.d("MSG", "Coroutine Started...")
                practiceVM.currentScale.catch { err ->
                    Log.d("MSG", err.toString())
                }.collect { msg ->
                   Log.d("MSG", msg)
                }

            }
        }

        binding.composeView?.setContent {
             val purple = Color(0xFF6805F2)
             val white = Color(0xffffffff)
             val blue = Color(0xff0F47F2)

             val darkColors = darkColors(
                primary = purple,
                secondary = white,
            )
             val lightColors = lightColors(
                primary = blue,
                secondary = white,
            )
            @Composable
            fun myButton(
                darkTheme: Boolean = isSystemInDarkTheme(),
            ){

                MaterialTheme(colors = if (darkTheme) darkColors else lightColors) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary,
                                contentColor = MaterialTheme.colors.secondary
                            ),
                            onClick = { next() },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 6.dp,
                                pressedElevation = 8.dp,
                                disabledElevation = 0.dp
                            ),
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp),
                            shape = CircleShape
                        ) {
                            Text(text = "Next", fontSize = 30.sp)
                        }
                    }
                }
            }
            myButton()
        }

        binding.practiceViewModel = practiceVM
        binding.lifecycleOwner = this

//        val practiceArray =  practiceVM.practiceArray


        //PracticeCycler.practiceArray = practiceArray.toMutableList()

        viewModel._msg.value = "Click Next to Begin"

        viewModel.isFav.observe(viewLifecycleOwner) {

            binding.starBtn?.setContent {

                class CustomShape : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {

                        var mid = (size.width / 2)
                        val min = Math.min(size.width, size.height)
                        val fat = min / 17
                        val half = min / 2
                        val rad = half - fat
                        mid -= half

                        val path = Path().apply {
                            // top left
                            moveTo(mid + half * 0.5f, half * 0.84f);
                            // top right
                            lineTo(mid + half * 1.5f, half * 0.84f);
                            // bottom left
                            lineTo(mid + half * 0.68f, half * 1.45f);
                            // top tip
                            lineTo(mid + half * 1.0f, half * 0.5f);
                            // bottom right
                            lineTo(mid + half * 1.32f, half * 1.45f);
                            // top left
                            lineTo(mid + half * 0.5f, half * 0.84f);

                            close()
                        }
                        return Outline.Generic(path)
                    }
                }

                val Purple = Color(0xFF6805F2)
                val Blue = Color(0xff0F47F2)
                val white = Color(0xFF868686)

                val DarkColors = darkColors(
                    primary = Purple,
                    secondary = white,
                )
                val LightColors = lightColors(
                    primary = Blue,
                    secondary = white,
                )

                @Composable
                fun star(darkTheme: Boolean = isSystemInDarkTheme(), toggled: Boolean) {
                    MaterialTheme(colors = if (darkTheme) DarkColors else LightColors) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (!toggled) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
                                    contentColor = MaterialTheme.colors.secondary
                                ),
                                onClick = { viewModel.handleFav() },
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 6.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp
                                ),

                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                shape = CustomShape()
                            ) {

                            }
                        }
                    }
                }
                star(toggled = it)
            }

        }

        viewModel.isEnd.observe(viewLifecycleOwner) {
            if (it == false) {
                binding.starBtn?.visibility = View.GONE
            } else {
                binding.starBtn?.visibility = View.VISIBLE
            }
        }

        return binding.root
    }


    private fun next(){
        //viewModel.next()
        practiceVM.nextScale()
        //viewModel.getProgress(size)
//        if(practiceVM.done){binding.composeView?.isVisible = false}

    }

    override fun onPause() {
        super.onPause()
        //viewModel.saveRecord()

//        practiceVM.savePracticeSession()

//        if(safeArgs.practicePackage.savedPractice){
//            viewModel.updatedSavedProgress(
//                    safeArgs.practicePackage.key,
//                    safeArgs.practicePackage.routine_name,
//                    safeArgs.practicePackage.practice_array,
//                    PracticeCycler.practiceArray.toTypedArray() as Array<PracticeSave>,
//                    safeArgs.practicePackage.total,
//                    safeArgs.practicePackage.date
//            )
//        }
    }

    override fun onResume() {
        super.onResume()
        //viewModel.loadRecord()
    }
}