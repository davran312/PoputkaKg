package poputka.neobis.poputkakg.ui.auth.login

import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_login.*
import poputka.neobis.poputkakg.R
import poputka.neobis.poputkakg.ui.main.BaseActivity

class LoginActivity : BaseActivity(),LoginView {

    @InjectPresenter
    lateinit var mPresenter:LoginPresenter
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        initButtonListeners()
    }

    private fun initButtonListeners() {
        etLogin.addTextChangedListener(mListener)
        etPassword.addTextChangedListener(mListener)
        btnLogin.setOnClickListener {
            hideKeyboard()
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()
            mPresenter.login(login,password)
        }
        tvForgetPassword.setOnClickListener {
            Snackbar.make(rootView,"Not ready yet", Toast.LENGTH_SHORT).show()
        }
        btnRegistration.setOnClickListener {
            Snackbar.make(rootView,"Not ready yet", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onSucces(result: Void?) {
        Snackbar.make(rootView,"Success", Toast.LENGTH_SHORT).show()

    }

    override fun onError(msg: String) {
        Snackbar.make(rootView,msg, Toast.LENGTH_SHORT).show()
    }

    override fun clearError() {
        mLoginLayout.error = ""
        mPassowrdLayout.error = ""
    }

    override fun onLoginError() {
        mLoginLayout.error=resources.getString(R.string.incorrectLogin)
    }

    override fun onPasswordError() {
        mPassowrdLayout.error = resources.getString(R.string.incorrectPassword)
    }
    private val mListener = object: poputka.neobis.poputkakg.utils.TextWatcher() {
        override fun onTextChanged(editable: Editable) {
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()
            btnLogin.isEnabled  = (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password))
            }
       }
    }

