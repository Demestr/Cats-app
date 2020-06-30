package com.example.catsapp.di

import android.content.Context
import com.example.catsapp.database.DatabaseModule
import com.example.catsapp.network.NetworkModule
import com.example.catsapp.ui.catslist.CatListComponent
import com.example.catsapp.ui.favorite.FavoritesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SubcomponentsModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun catListComponent(): CatListComponent.Factory
    fun inject(fragment: FavoritesFragment)
}