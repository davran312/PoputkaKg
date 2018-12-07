package poputka.neobis.poputkakg.ui.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import poputka.neobis.poputkakg.utils.IProgressBar

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView :MvpView, IProgressBar {

}