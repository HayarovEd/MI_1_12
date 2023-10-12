package heya.money.mama.presentation

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import heya.money.mama.domain.model.StatusApplication.Connect
import heya.money.mama.domain.model.StatusApplication.Info
import heya.money.mama.domain.model.StatusApplication.Loading
import heya.money.mama.domain.model.StatusApplication.Mock
import heya.money.mama.domain.model.StatusApplication.NoConnect
import heya.money.mama.domain.model.StatusApplication.Offer
import heya.money.mama.domain.model.StatusApplication.Web
import heya.money.mama.domain.model.TypeCard
import heya.money.mama.domain.model.basedto.BaseState
import heya.money.mama.domain.model.basedto.BaseState.Cards
import heya.money.mama.presentation.MainEvent.OnChangeBaseState
import heya.money.mama.presentation.MainEvent.OnChangeStatusApplication
import heya.money.mama.ui.presentation_v1.BaseScreen
import heya.money.mama.domain.model.basedto.BaseState.WebPrimary
import java.io.File
import java.util.concurrent.ExecutorService

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Sample(
    viewModel: MainViewModel = hiltViewModel(),
    outputDirectory: File,
    executor: ExecutorService
) {

    val state = viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent
    val context = LocalContext.current

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
           Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            //Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    val loanLazyState = rememberLazyListState()
    val creditLazyState = rememberLazyListState()
    val creditCardloanLazyState = rememberLazyListState()
    val debitCardLazyState = rememberLazyListState()
    val instalmentCardLazyState = rememberLazyListState()
    val typeCard = if (!state.value.creditCards.isNullOrEmpty()) TypeCard.CardCredit
    else if (!state.value.debitCards.isNullOrEmpty()) TypeCard.CardDebit else TypeCard.CardInstallment
    when (val currentState = state.value.statusApplication) {
        is Connect -> {
            ConnectScreen(
                baseState = currentState.baseState,
                db = state.value.dbData!!,
                onClickCards = { onEvent(
                    OnChangeBaseState(
                        Cards(
                    typeCard = typeCard
                ))
                ) },
                onClickCredits = { onEvent(OnChangeBaseState(BaseState.Credits)) },
                onClickLoans = { onEvent(OnChangeBaseState(BaseState.Loans)) },
                onClickRules = {
                    onEvent(
                        OnChangeStatusApplication(
                            Info(
                                currentBaseState = currentState.baseState,
                                content = state.value.dbData!!.appConfig.privacyPolicyHtml
                            )
                        )
                    )
                },
                onClickPrimary = {
                    onEvent(OnChangeBaseState(WebPrimary(
                        offerName = state.value.dbData!!.appConfig.namePrimary?:"",
                        url = state.value.dbData!!.appConfig.urlPrimary?:""
                    )))
                },
                loanLazyState = loanLazyState,
                creditLazyState = creditLazyState,
                creditCardloanLazyState = creditCardloanLazyState,
                debitCardLazyState = debitCardLazyState,
                instalmentCardLazyState = instalmentCardLazyState,
                creditCards = state.value.creditCards,
                debitCards = state.value.debitCards,
                installmentCards = state.value.installmentCards,
                launcherMultiplePermissions = launcherMultiplePermissions,
                context = context,
                onEvent = viewModel::onEvent
            )
        }

        Loading -> {
            LoadingScreen()
        }

        is Mock -> {
            BaseScreen(
                outputDirectory = outputDirectory,
                executor = executor,
            )
        }

        is Info -> {
            PrivacyScreen(
                content = state.value.dbData?.appConfig?.privacyPolicyHtml?:"",
                baseState = (state.value.statusApplication as Info).currentBaseState,
                onEvent = viewModel::onEvent
            )
        }

        is Offer -> {
            OfferScreen(
                elementOffer = (state.value.statusApplication as Offer).elementOffer,
                baseState = (state.value.statusApplication as Offer).currentBaseState,
                onEvent = viewModel::onEvent,
                launcherMultiplePermissions = launcherMultiplePermissions,
                context = context
            )
        }

        is Web -> {
            WebViewScreen(
                url = currentState.url,
                offerName = currentState.offerName,
                onEvent = viewModel::onEvent,
            )
        }

        NoConnect -> {
            NoInternetScreen(onEvent = viewModel::onEvent)
        }
    }

}