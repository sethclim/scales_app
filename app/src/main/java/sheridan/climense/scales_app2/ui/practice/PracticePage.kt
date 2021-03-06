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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.features.practice.PracticeContract
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import sheridan.climense.kmmsharedmodule.features.practice.PracticeViewModel
import java.time.LocalDate
import java.time.ZoneId

class PracticePage : Fragment() {

    private lateinit var binding : PracticePageFragmentBinding

    private val practiceVM: PracticeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PracticePageFragmentBinding.inflate(inflater, container, false)

        val date = LocalDate.now()
        val zoneId: ZoneId = ZoneId.systemDefault() // or: ZoneId.of("Europe/Oslo");
        val epoch: Long = date.atStartOfDay(zoneId).toEpochSecond()

        practiceVM.setEvent(PracticeContract.Event.SetTodayDate(epoch))

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
                            onClick = { practiceVM.setEvent(PracticeContract.Event.OnGetScale) },
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
        binding.lifecycleOwner = viewLifecycleOwner

            binding.starBtn?.setContent {

                class CustomShape : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {

                        var mid = (size.width / 2)
                        val min = Math.min(size.width, size.height)
                        val half = min / 2
                        mid -= half

                        val path = Path().apply {
                            // top left
                            moveTo(mid + half * 0.5f, half * 0.84f)
                            // top right
                            lineTo(mid + half * 1.5f, half * 0.84f)
                            // bottom left
                            lineTo(mid + half * 0.68f, half * 1.45f)
                            // top tip
                            lineTo(mid + half * 1.0f, half * 0.5f)
                            // bottom right
                            lineTo(mid + half * 1.32f, half * 1.45f)
                            // top left
                            lineTo(mid + half * 0.5f, half * 0.84f)

                            close()
                        }
                        return Outline.Generic(path)
                    }
                }

                val Purple = Color(0xFF6805F2)
                val Blue = Color(0xff0F47F2)
                val white = Color(0xFF868686)

                val darkColors = darkColors(
                    primary = Purple,
                    secondary = white,
                )
                val lightColors = lightColors(
                    primary = Blue,
                    secondary = white,
                )

                @Composable
                fun star(darkTheme: Boolean = isSystemInDarkTheme()) {
                    val state by practiceVM.uiState.collectAsState()

                    Log.d("STATE", "$state")

                    MaterialTheme(colors = if (darkTheme) darkColors else lightColors) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {

                            val practice = state.practice.accessData()

                            Log.d("Fav", practice?.isFav.toString())

                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (practice != null && !practice.isFav) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
                                    contentColor = MaterialTheme.colors.secondary
                                ),
                                onClick = {
                                    if(practice != null && practice.isFav){
                                        practiceVM.setEvent(PracticeContract.Event.RemoveFavourite)
                                    }
                                    else{
                                        practiceVM.setEvent(PracticeContract.Event.AddFavourite)
                                    }
                                },
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
                star()
            }

        return binding.root
    }

    override fun onPause() {
        super.onPause()

        practiceVM.setEvent(PracticeContract.Event.SavePracticeSession)
    }

    override fun onResume() {
        super.onResume()
        //viewModel.loadRecord()
    }
}