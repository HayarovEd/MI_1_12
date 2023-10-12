package heya.money.mama.data

import android.util.Log
import heya.money.mama.data.Resource.Error
import heya.money.mama.data.Resource.Success
import javax.inject.Inject
import heya.money.mama.domain.RepositoryServer
import heya.money.mama.domain.model.basedto.BaseDto

class RepositoryServerImpl @Inject constructor(
    private val apiServer: ApiServer
) : RepositoryServer {
    override suspend fun getDataDb(): Resource<BaseDto> {
        return try {
            val folder = apiServer.getDataDb()
            Log.d("DATADB", "dATA DB:${folder.loans.first().id}")
            Success(
                data = folder
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e.message ?: "An unknown error")
        }
    }
}