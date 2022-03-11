package com.example.composeguitests

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeguitests.ui.theme.ComposeGuiTestsTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Pressure 1",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(130.dp))
                    Text(text = "Pressure 2",
                        fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(256.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center


                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomIconButton(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowUp,
                            onPress = {
                                println("Left Up Pressed")
                            },
                            onRelease = {
                                println("Left Up Released")
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomIconButton(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowDown,
                            onPress = {
                                println("Left Down Pressed")
                            },
                            onRelease = {
                                println("Left Down Released")
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomIconButton(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowUp,
                            onPress = {
                                println("Both Up Pressed")
                            },
                            onRelease = {
                                println("Both Up Released")
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomIconButton(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowDown,
                            onPress = {
                                println("Both Down Pressed")
                            },
                            onRelease = {
                                println("Both Down Released")
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomIconButton(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowUp,
                            onPress = {
                                println("Right Up Pressed")
                            },
                            onRelease = {
                                println("Right Up Released")
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomIconButton(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            icon = Icons.Default.KeyboardArrowDown,
                            onPress = {
                                println("Right Down Pressed")
                            },
                            onRelease = {
                                println("Right Down Released")
                            }
                        )
                    }

                }
            }
        }
    }
}

fun Modifier.actionWithRippleEffect(
    interactionSource: MutableInteractionSource,
    onPress: () -> Unit,
    onRelease: () -> Unit
): Modifier = composed {
    pointerInput(interactionSource){ //add enabled
        forEachGesture {
            coroutineScope {
                awaitPointerEventScope {
                    val down = awaitFirstDown(requireUnconsumed = false)
                    val pressed = PressInteraction.Press(down.position)
                    val job = launch {
                        interactionSource.emit(pressed)
                        onPress()
                    }

                    val up = waitForUpOrCancellation()
                    job.cancel()

                    val releaseOrCancel = when(up){
                        null -> PressInteraction.Cancel(pressed)
                        else -> PressInteraction.Release(pressed)
                    }

                    launch {
                        interactionSource.emit(releaseOrCancel)
                        onRelease()
                    }
                }
            }
        }
    }.indication(interactionSource, rememberRipple())
}

@Composable
fun CustomIconButton(
    modifier: Modifier,
    icon: ImageVector,
    onPress: () -> Unit,
    onRelease: () -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = modifier
        .actionWithRippleEffect(
            interactionSource = interactionSource,
            onPress = onPress,
            onRelease = onRelease
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                //.background(color = MaterialTheme.colors.primaryVariant)
        )
    }
}