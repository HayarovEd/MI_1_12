package heya.money.mama.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import heya.money.mama.R
import heya.money.mama.R.string
import heya.money.mama.presentation.MainEvent.Reconnect
import heya.money.mama.ui.theme.absoluteDark
import heya.money.mama.ui.theme.green
import heya.money.mama.ui.theme.white

@Composable
fun NoInternetScreen(
    modifier: Modifier = Modifier,
    onEvent: (MainEvent) -> Unit
) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(color = absoluteDark)
    ) {
        Column (
            modifier = modifier.align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(imageVector = ImageVector.vectorResource(
                id = R.drawable.no_connect), 
                contentDescription = "")
            Spacer(modifier = modifier.height(40.dp))
            Text(
                text = stringResource(id = string.not_connect),
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle(R.font.soyuz_grotesk_bold),
                color = green,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = stringResource(id = string.try_internet),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle(R.font.onest_400),
                color = white,
                textAlign = TextAlign.Center
            )
        }
        OutlinedButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(alignment = Alignment.BottomCenter),
            onClick = { onEvent(Reconnect) }) {
            Text(
                text = stringResource(id = string.reconnect),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle(R.font.soyuz_grotesk_bold),
                color = white,
                textAlign = TextAlign.Center
            )
        }
    }
}