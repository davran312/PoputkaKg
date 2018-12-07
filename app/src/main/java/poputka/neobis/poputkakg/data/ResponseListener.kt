package poputka.neobis.poputkakg.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ResponseListener<T>:Callback<T>{
    override fun onResponse(call: Call<T>, response: Response<T>) {

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        when(t){
            is SocketTimeoutException->{
                onError(Errors.SOCKET_EXCEPTION,"Время запроса истекло")
            }
            is UnknownHostException ->{
                onError(Errors.UNKNOWN_HOST,"Сервер не отвечает")
            }
            is ConnectException->{
                onError(Errors.CONNECT_EXCEPTION,"Ошибка подключения, Проверьте интернет соединение.")
            }
            else ->{
                onError(Errors.UNKNOWN,"Неизвестная ошибка "+t.message)
            }
        }
    }
    abstract fun onSuccess(response: Response<T>)
    abstract fun onError(type:Errors,errorMessage:String)
}