package com.kgram.utils.kodein

import org.kodein.di.*

/**
 * Shortcut for binding eager singletons to the same type.
 */
inline fun <reified T : Any> DI.MainBuilder.bindEagerSingleton(crossinline singleton: (DI) -> T) {
    bind<T>() with eagerSingleton { singleton(di) }
}

/**
 * Shortcut for binding singletons to the same type.
 */
inline fun <reified T : Any> DI.MainBuilder.bindSingleton(crossinline singleton: (DI) -> T) {
    bind<T>() with singleton { singleton(di) }
}