package ru.dvc.fandroidtop100crypt.di

import dagger.Component
import ru.dvc.fandroidtop100crypt.MainActivity
import ru.dvc.fandroidtop100crypt.adapter.CurrenciesAdapter
import ru.dvc.fandroidtop100crypt.fragments.CurrenciesListFragment
import ru.dvc.fandroidtop100crypt.mvp.contract.LatestChartContract
import ru.dvc.fandroidtop100crypt.mvp.presenter.CurrenciesPresenter
import ru.dvc.fandroidtop100crypt.mvp.presenter.LatestChartPresenter
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        AppModule::class,
        RestModule::class,
        MvpModule::class,
        ChartModule::class
    )
)
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
}