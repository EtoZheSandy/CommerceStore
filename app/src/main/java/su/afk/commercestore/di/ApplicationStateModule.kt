package su.afk.commercestore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import su.afk.commercestore.redux.ApplicationState
import su.afk.commercestore.redux.Store
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationStateModule {

    @Provides
    @Singleton
    fun provideApplicationStateStore(): Store<ApplicationState> {
        return Store(ApplicationState())
    }


}