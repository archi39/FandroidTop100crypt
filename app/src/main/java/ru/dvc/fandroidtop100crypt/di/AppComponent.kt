package ru.dvc.fandroidtop100crypt.di

import dagger.Component
import ru.dvc.fandroidtop100crypt.MainActivity
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
}