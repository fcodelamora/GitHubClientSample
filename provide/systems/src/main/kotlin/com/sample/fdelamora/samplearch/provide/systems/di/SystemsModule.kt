package com.sample.fdelamora.samplearch.provide.systems.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SystemsModule {
//    Sample:
//    @Binds
//    fun bindXxxxxxxxSystem(
//        xxxxxxxxSystem: XxxxxxxxSystem
//    ): IXxxxxxxxSystem
}
