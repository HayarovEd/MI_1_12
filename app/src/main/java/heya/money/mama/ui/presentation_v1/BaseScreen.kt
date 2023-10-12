package heya.money.mama.ui.presentation_v1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import heya.money.mama.ui.presentation_v1.ScreenState.BaseData
import heya.money.mama.ui.presentation_v1.ScreenState.Card
import heya.money.mama.ui.presentation_v1.ScreenState.Confirm
import heya.money.mama.ui.presentation_v1.ScreenState.DateTime
import heya.money.mama.ui.presentation_v1.ScreenState.Selfie
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun  BaseScreen(
    outputDirectory: File,
    executor: ExecutorService,
) {
    val setScreen: MutableState<ScreenState> = remember {
        mutableStateOf(Card)
    }
    val dateMeet: MutableState<String> = remember {
        mutableStateOf("")
    }
    val timeMeet: MutableState<String> = remember {
        mutableStateOf("")
    }
    when (setScreen.value) {
        BaseData -> {
            UserDataScreen(
                setScreen = setScreen
            )
        }
        Card -> {
            CardScreen(
                setScreen = setScreen
            )
        }
        Confirm -> {
            ConfirmScreen(
                dateMeet = dateMeet.value,
                timeMeet = timeMeet.value
            )
        }
        DateTime -> {
            DateMeetScreen(
                setScreen = setScreen,
                dateMeet = dateMeet,
                timeMeet = timeMeet
            )
        }
        Selfie -> {
            SelfieScreen(
                setScreen = setScreen,
                outputDirectory = outputDirectory,
                executor = executor,
            )
        }
    }
}