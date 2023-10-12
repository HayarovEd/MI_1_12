package com.fast.zai.loans.presentation

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fast.zai.loans.R
import com.fast.zai.loans.domain.model.TypeCard
import com.fast.zai.loans.domain.model.TypeCard.CardCredit
import com.fast.zai.loans.domain.model.TypeCard.CardDebit
import com.fast.zai.loans.domain.model.TypeCard.CardInstallment
import com.fast.zai.loans.domain.model.basedto.BaseState
import com.fast.zai.loans.domain.model.basedto.CardsCredit
import com.fast.zai.loans.domain.model.basedto.CardsDebit
import com.fast.zai.loans.domain.model.basedto.CardsInstallment
import com.fast.zai.loans.presentation.MainEvent.OnChangeTypeCard
import com.fast.zai.loans.ui.theme.absoluteDark
import com.fast.zai.loans.ui.theme.baseBackground
import com.fast.zai.loans.ui.theme.green

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    valuePaddings: PaddingValues,
    creditCards: List<CardsCredit>,
    debitCards: List<CardsDebit>,
    installmentCards: List<CardsInstallment>,
    typeCard: TypeCard,
    onEvent: (MainEvent) -> Unit,
    baseState: BaseState,
    launcherMultiplePermissions: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    context: Context,
    creditCardloanLazyState: LazyListState,
    debitCardLazyState: LazyListState,
    instalmentCardLazyState: LazyListState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = baseBackground)
            .padding(valuePaddings),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!creditCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier.weight(1f),
                    onClick = { onEvent(OnChangeTypeCard(CardCredit)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (typeCard is TypeCard.CardCredit) green else baseBackground
                    ),
                    contentPadding = PaddingValues(horizontal = 11.dp, vertical = 8.dp)
                ) {
                    Text(
                        color = absoluteDark,
                        fontStyle = FontStyle(R.font.inter_600),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = stringResource(id = R.string.credit),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (!debitCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier.weight(1f),
                    onClick = { onEvent(OnChangeTypeCard(CardDebit)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (typeCard is TypeCard.CardDebit) green else baseBackground
                    ),
                    contentPadding = PaddingValues(horizontal = 11.dp, vertical = 8.dp)
                ) {
                    Text(
                        color = absoluteDark,
                        fontStyle = FontStyle(R.font.inter_600),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = stringResource(id = R.string.debit),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (!installmentCards.isNullOrEmpty()) {
                Button(
                    modifier = modifier.weight(1f),
                    onClick = { onEvent(OnChangeTypeCard(CardInstallment)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (typeCard is TypeCard.CardInstallment) green else baseBackground
                    ),
                    contentPadding = PaddingValues(horizontal = 11.dp, vertical = 8.dp)
                ) {
                    Text(
                        color = absoluteDark,
                        fontStyle = FontStyle(R.font.inter_600),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = stringResource(id = R.string.installment),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(24.dp))
        when (typeCard) {
            TypeCard.CardCredit -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = creditCardloanLazyState
                ) {
                    items(creditCards) { card ->
                        ItemCreditCard(
                            card = card,
                            onEvent = onEvent,
                            baseState = baseState,
                            launcherMultiplePermissions = launcherMultiplePermissions,
                            context = context
                        )
                    }
                }
            }

            TypeCard.CardDebit -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = debitCardLazyState
                ) {
                    items(debitCards) { card ->
                        ItemDebitCard(
                            card = card,
                            onEvent = onEvent,
                            baseState = baseState,
                            launcherMultiplePermissions = launcherMultiplePermissions,
                            context = context
                        )
                    }
                }
            }

            TypeCard.CardInstallment -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = instalmentCardLazyState
                ) {
                    items(installmentCards) { card ->
                        ItemInstallmentCard(
                            card = card,
                            baseState = baseState,
                            onEvent = onEvent,
                            launcherMultiplePermissions = launcherMultiplePermissions,
                            context = context
                        )
                    }
                }
            }
        }

    }
}