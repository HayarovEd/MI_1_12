package heya.money.mama.presentation

import heya.money.mama.domain.model.StatusApplication
import heya.money.mama.domain.model.TypeCard
import heya.money.mama.domain.model.basedto.BaseState

sealed class MainEvent {
    object Reconnect: MainEvent()

    class OnChangeStatusApplication(val statusApplication: StatusApplication): MainEvent()
    class OnChangeBaseState(val baseState: BaseState): MainEvent()
    class OnChangeTypeCard(val typeCard: TypeCard): MainEvent()

    class OnGoToWeb(
        val urlOffer: String,
        val nameOffer: String
        ): MainEvent()
}
