package poputka.neobis.poputkakg.ui.main

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import poputka.neobis.poputkakg.R
import poputka.neobis.poputkakg.mvp_view.MvpAppCompatActivity

open  abstract class BaseActivity : MvpAppCompatActivity(){
    abstract fun getLayoutResId():Int
    abstract fun init()
    private var progressBar :ProgressDialog? = null
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun showProgress() {
        this.runOnUiThread {
            progressBar == null
            if (progressBar == null && !isFinishing) {
                progressBar = ProgressDialog(this)
                progressBar?.setMessage(getString(R.string.loading))
                progressBar?.setCanceledOnTouchOutside(false)
                progressBar?.show()
            }
        }
    }

    open fun hideProgress() {
        this.runOnUiThread {
            if (progressBar != null && progressBar?.isShowing!!) {
                progressBar!!.dismiss()
                progressBar = null
            }
        }
    }
    protected fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
        inputManager?.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }



}