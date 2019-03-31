package ru.dvc.fandroidtop100crypt.mvp.presenter

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dvc.fandroidtop100crypt.adapter.CurrenciesAdapter
import ru.dvc.fandroidtop100crypt.di.App
import ru.dvc.fandroidtop100crypt.formatThousands
import ru.dvc.fandroidtop100crypt.mvp.contract.CurrenciesContract
import ru.dvc.fandroidtop100crypt.rest.CoinGeckoApi
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeList() {
        view.showProgress()

        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it) }
            .doOnNext {
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )

    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}