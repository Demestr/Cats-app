package com.example.catsapp.di

import com.example.catsapp.ui.catslist.CatListComponent
import dagger.Module

@Module(subcomponents = [CatListComponent::class])
class SubcomponentsModule