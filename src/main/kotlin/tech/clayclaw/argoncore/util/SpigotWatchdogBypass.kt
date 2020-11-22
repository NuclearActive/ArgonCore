package tech.clayclaw.argoncore.util

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun bypassWatchdog(content: () -> Unit) {
    val spigotWatchDogFeeder: Disposable = Class.forName("org.spigotmc.WatchdogThread")!!.let { watchDogClass ->
        Observable.interval(50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe {
                    watchDogClass.getMethod("tick")!!.invoke(null)
                }
    }
    content()
    spigotWatchDogFeeder.dispose()
}
