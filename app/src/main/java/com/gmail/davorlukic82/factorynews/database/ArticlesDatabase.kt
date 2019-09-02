package com.gmail.davorlukic82.factorynews.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.davorlukic82.factorynews.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    companion object {
        private var instance: ArticlesDatabase? = null

        fun getInstance(context: Context): ArticlesDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, ArticlesDatabase::class.java, "ArticlesDatabase")
                        .allowMainThreadQueries().build()
            }
            return instance as ArticlesDatabase
        }
    }
}