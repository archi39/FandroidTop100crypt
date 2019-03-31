package ru.dvc.fandroidtop100crypt.di

import dagger.Module
import dagger.Provides
import ru.dvc.fandroidtop100crypt.mvp.presenter.CurrenciesPresenter
import ru.dvc.fandroidtop100crypt.mvp.presenter.LatestChartPresenter
import javax.inject.Singleton

@Module
class MvpModule {
    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()
}