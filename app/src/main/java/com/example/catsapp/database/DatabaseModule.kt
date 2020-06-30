package com.example.catsapp.database

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCatsDao(context: Context): CatsDao{
        return CatsDatabase.getInstance(context).getDao()
    }
}