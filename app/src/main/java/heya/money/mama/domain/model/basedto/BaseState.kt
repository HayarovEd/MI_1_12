package heya.money.mama.domain.model.basedto

import heya.money.mama.domain.model.TypeCard

sealed class BaseState{
    object Loans: BaseState()
    class Cards(val typeCard: TypeCard): BaseState()
    class WebPrimary (
        val url: String,
        val offerName: String
    ): BaseState()
    object Credits: BaseState()
}
