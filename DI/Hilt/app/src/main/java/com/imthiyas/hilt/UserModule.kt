package com.imthiyas.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ServiceComponent


@InstallIn(ServiceComponent::class)
@Module
class UserModule {

    @Provides
    fun provideUserRepo(): UserRepo {
        return AWSRepository()
    }
}