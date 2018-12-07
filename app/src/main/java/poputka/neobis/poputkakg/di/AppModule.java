package poputka.neobis.poputkakg.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import poputka.neobis.poputkakg.utils.AndroidUtilities;
import poputka.neobis.poputkakg.utils.Preference;

import javax.inject.Singleton;

@Singleton
@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    AndroidUtilities provideUtilities(){
        return new AndroidUtilities(mContext);
    }
    @Singleton
    @Provides
    Preference providePreference(){
        return new Preference(mContext);
    }

}
