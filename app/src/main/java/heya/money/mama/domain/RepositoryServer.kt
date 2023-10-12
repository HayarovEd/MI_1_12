package heya.money.mama.domain

import heya.money.mama.data.Resource
import heya.money.mama.domain.model.basedto.BaseDto

interface RepositoryServer {
    suspend fun getDataDb() : Resource<BaseDto>
}