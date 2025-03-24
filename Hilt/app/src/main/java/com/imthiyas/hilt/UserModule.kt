package com.imthiyas.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @FirebaseQualifier
    fun providesFirebaseRepo(firebaseRepository: FirebaseRepository): UserRepo {
        return firebaseRepository
    }


    @Provides
    @AWSQualifier
    fun providesAWSRepository(): UserRepo {
        return AWSRepository()
    }

}