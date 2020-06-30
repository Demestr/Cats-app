package com.example.catsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catsapp.model.Breed
import com.example.catsapp.model.Cat
import com.example.catsapp.model.Category
import com.example.catsapp.model.dbclasses.CatBreedsCategoryCrossRef
import com.example.catsapp.model.dbclasses.WeightConverter

@Database(entities = [Cat::class, Breed::class, Category::class, CatBreedsCategoryCrossRef::class], version = 2, exportSchema = false)
@TypeConverters(WeightConverter::class)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun getDao(): CatsDao

    companion object {

        @Volatile
        private var INSTANCE: CatsDatabase? = null

        fun getInstance(context: Context) : CatsDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatsDatabase::class.java,
                        "cats_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}