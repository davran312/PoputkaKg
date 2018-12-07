package poputka.neobis.poputkakg.mvp_view
/**
 * Date: 17.12.2015
 * Time: 14:34
 *
 * @author Yuri Shmakov
 * @author Alexander Bliniov
 * @author Konstantin Tckhovrebov
 */
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.MvpDelegate

open class MvpAppCompatActivity:AppCompatActivity(){
    private var mMvpDelegate: MvpDelegate<*> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMvpDelegate().onCreate(savedInstanceState)
    }
    fun getMvpDelegate():MvpDelegate<*>{
        if(mMvpDelegate == null)
            mMvpDelegate = MvpDelegate(this)
        return mMvpDelegate!! }

    override fun onStart() {
        super.onStart()
        getMvpDelegate().onAttach() }

    override fun onResume() {
        super.onResume()
        getMvpDelegate().onAttach() }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        getMvpDelegate().onSaveInstanceState(outState)
        getMvpDelegate().onDetach()
    }

    override fun onStop() {
        super.onStop()
        getMvpDelegate().onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate().onDestroyView()

        if(isFinishing){
            getMvpDelegate().onDestroy()
        }
    }

}