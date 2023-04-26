package com.sample.fdelamora.samplearch.provide.datasources.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

//    Sample
//    @Binds
//    fun bindUserPreferencesDataSource(
//        dataSource: SharedPreferencesUserPreferencesDataSource
//    ): IUserPreferencesDataSource
}
