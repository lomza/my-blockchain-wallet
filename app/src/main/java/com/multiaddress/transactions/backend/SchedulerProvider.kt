package com.multiaddress.transactions.backend

import dagger.Reusable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@Reusable
class SchedulerProvider @Inject constructor() {
    fun ioScheduler(): Scheduler = Schedulers.io()
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
