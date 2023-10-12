package heya.money.mama.data

import heya.money.mama.domain.model.basedto.BaseDto
import retrofit2.http.GET

interface ApiServer {
    @GET ("b8b203c2_Nov/db.json")
    suspend fun getDataDb () : BaseDto
}