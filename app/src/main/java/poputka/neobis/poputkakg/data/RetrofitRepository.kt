package poputka.neobis.poputkakg.data

import com.google.gson.JsonElement
import poputka.neobis.poputkakg.models.LoginModel
import poputka.neobis.poputkakg.models.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitRepository {

@POST
    fun login(@Url url: String,@Body  body:LoginModel):Call<Token>


}