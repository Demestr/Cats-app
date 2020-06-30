package com.example.catsapp.ui.catslist

import dagger.Subcomponent

@Subcomponent
interface CatListComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): CatListComponent
    }

    fun inject(fragment: ListFragment)
}