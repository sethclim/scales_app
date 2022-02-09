package sheridan.climense.scales_app2.ui.practice


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import sheridan.climense.kmmsharedmodule.domain.PracticeCycler
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.scales_app2.models.PracticeSave

class PracticePage : Fragment() {

    private val safeArgs: PracticePageArgs by navArgs()
    private val viewModel: PracticePageViewModel by viewModels()
    private lateinit var binding : PracticePageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PracticePageFragmentBinding.inflate(inflater, container, false)

        val totalLength = safeArgs.practicePackage.total
        binding.composeView?.setContent {
             val Purple = Color(0xFF6805F2)
             val white = Color(0xffffffff)
             val Blue = Color(0xff0F47F2)

             val DarkColors = darkColors(
                primary = Purple,
                secondary = white,
            )
             val LightColors = lightColors(
                primary = Blue,
                secondary = white,
            )
            @Composable
            fun myButton(
                darkTheme: Boolean = isSystemInDarkTheme(),
            ){
                MaterialTheme(colors = if (darkTheme) DarkColors else LightColors) {
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
                            onClick = { next(totalLength) },
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

        binding.practiceViewModel = viewModel
        binding.lifecycleOwner = this

        val practiceArray = safeArgs.practicePackage.practice_array as Array<Practice>


        PracticeCycler.practiceArray = practiceArray.toMutableList()

        viewModel._msg.value = "Click Next to Begin"

        viewModel.isFav.observe(viewLifecycleOwner, {

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
                fun star(darkTheme: Boolean = isSystemInDarkTheme(), toggled: Boolean ){
                    MaterialTheme(colors = if (darkTheme) DarkColors else LightColors) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if(!toggled) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
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

        })

        viewModel.isEnd.observe(viewLifecycleOwner, {
            if(it == false){binding.starBtn?.visibility = View.GONE}else{binding.starBtn?.visibility = View.VISIBLE}
        })

        return binding.root
    }


    private fun next(size : Int){
        viewModel.next()
        viewModel.getProgress(size)
        if(viewModel.done){binding.composeView?.isVisible = false}

    }

    override fun onPause() {
        super.onPause()
        viewModel.saveRecord()

        if(safeArgs.practicePackage.savedPractice){
            viewModel.updatedSavedProgress(
                    safeArgs.practicePackage.key,
                    safeArgs.practicePackage.routine_name,
                    safeArgs.practicePackage.practice_array,
                    PracticeCycler.practiceArray.toTypedArray() as Array<PracticeSave>,
                    safeArgs.practicePackage.total,
                    safeArgs.practicePackage.date
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecord()
    }
}