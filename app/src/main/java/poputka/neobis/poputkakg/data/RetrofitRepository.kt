package poputka.neobis.poputkakg.data

import retrofit2.http.GET

interface RetrofitRepository {
    @GET("")
    fun getCountryList()
}