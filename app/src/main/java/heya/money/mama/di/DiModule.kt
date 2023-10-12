package heya.money.mama.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import heya.money.mama.data.RepositoryAnalyticImpl
import heya.money.mama.data.RepositoryServerImpl

import heya.money.mama.data.ServiceImpl
import heya.money.mama.data.SharedKeeperImpl
import hed.hotzaem.tophh.gola.domain.RepositoryAnalytic
import heya.money.mama.domain.RepositoryServer
import heya.money.mama.domain.Service
import heya.money.mama.domain.SharedKepper


@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {

    @Binds
    @Singleton
    abstract fun bindService(service: ServiceImpl): Service

    @Binds
    @Singleton
    abstract fun bindKeeper(sharedKeeper: SharedKeeperImpl): SharedKepper

    @Binds
    @Singleton
    abstract fun bindRepositoryAnalytic(repository: RepositoryAnalyticImpl): RepositoryAnalytic

    @Binds
    @Singleton
    abstract fun bindRepositoryServer(repository: RepositoryServerImpl): RepositoryServer

}