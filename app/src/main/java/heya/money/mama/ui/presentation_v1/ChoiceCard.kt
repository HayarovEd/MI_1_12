package heya.money.mama.ui.presentation_v1

sealed interface ChoiceCard{
    object NotChoice: ChoiceCard
    object ChoiceVisa: ChoiceCard
    object ChoiceMaestro: ChoiceCard
}