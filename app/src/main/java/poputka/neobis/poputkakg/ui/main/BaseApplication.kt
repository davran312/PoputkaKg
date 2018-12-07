package poputka.neobis.poputkakg.ui.main

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import poputka.neobis.poputkakg.di.AppComponent
import poputka.neobis.poputkakg.di.AppModule
import poputka.neobis.poputkakg.di.DaggerAppComponent
import poputka.neobis.poputkakg.di.NetworkModule
import poputka.neobis.poputkakg.utils.AndroidUtilities
import javax.inject.Inject

class BaseApplication : Application(){
    @Inject
    lateinit var mUtility :AndroidUtilities

    companion object {
        lateinit var sComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        sComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).networkModule(NetworkModule()).build()
        sComponent.inject(this)


    }





}