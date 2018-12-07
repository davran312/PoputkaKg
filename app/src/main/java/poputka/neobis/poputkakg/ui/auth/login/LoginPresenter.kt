package poputka.neobis.poputkakg.ui.auth.login

import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import poputka.neobis.poputkakg.data.Errors
import poputka.neobis.poputkakg.data.ResponseListener
import poputka.neobis.poputkakg.data.RetrofitRepository
import poputka.neobis.poputkakg.models.LoginModel
import poputka.neobis.poputkakg.models.Token
import poputka.neobis.poputkakg.ui.main.BaseApplication
import poputka.neobis.poputkakg.utils.Const
import poputka.neobis.poputkakg.utils.Preference
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class LoginPresenter:MvpPresenter<LoginView>(){
    @Inject
    lateinit var service:RetrofitRepository
    @Inject
    lateinit var mPreference: Preference

    init {
        BaseApplication.sComponent.inject(this)
    }
    fun login(login:String?,password:String?){
        viewState.clearError()
        if(check(login,password)){
            viewState.showProgress()
            val loginModel = createLoginModel(login,password)
            service.login(Const.login,loginModel).enqueue(object :ResponseListener<Token>(){
                override fun onSuccess(response: Response<Token>) {
                    viewState.hideProgress()
                    if(response.isSuccessful && response.body() != null){
                        updateUserInfo(response.body())
                        viewState.onSucces(null)
                    }
                    else{
                        viewState.onError(response.message())
                        viewState.hideProgress()

                    }
                }
                override fun onError(type: Errors, errorMessage: String) {
                    viewState.onError(errorMessage)
                    viewState.hideProgress()
                }

            })
        }
    }

    private fun updateUserInfo(body: Token?) {
        mPreference.setToken(body!!.token)
        mPreference.setUserId(body!!.user_id)
    }

    private fun createLoginModel(login: String?, password: String?): LoginModel {
        return LoginModel(login!!,password!!)
    }

    private fun check(login:String?,password:String?):Boolean{
        if(TextUtils.isEmpty(login)){
            viewState?.onLoginError()
            return false
        }
        if(TextUtils.isEmpty(password)){
            viewState?.onPasswordError()
            return false
        }
        return true
    }
}