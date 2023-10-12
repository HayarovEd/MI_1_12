package heya.money.mama.domain.model

sealed interface TypeCard{
    object CardCredit: TypeCard
    object CardDebit: TypeCard
    object CardInstallment: TypeCard
}