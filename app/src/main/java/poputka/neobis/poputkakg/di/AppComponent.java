package poputka.neobis.poputkakg.di;

import dagger.Component;
import org.jetbrains.annotations.NotNull;
import poputka.neobis.poputkakg.ui.main.BaseApplication;

import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class,AppModule.class})
public interface AppComponent {


    void inject(@NotNull BaseApplication mApplication);

}
