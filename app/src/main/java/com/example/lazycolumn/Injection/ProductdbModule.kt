package com.example.lazycolumn.Injection

import android.app.Application
import com.example.lazycolumn.Database.ProductDataSource
import com.example.lazycolumn.ItemsDatabase
import com.example.lazycolumn.Repository.ProductDataRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductdbModule {
    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = ItemsDatabase.Schema,
            context = app,
            name = "items.db"
        )
    }

    @Provides
    @Singleton
    fun providePersonDataSource(driver: SqlDriver): ProductDataSource {
        return ProductDataRepository(ItemsDatabase(driver))
    }


}