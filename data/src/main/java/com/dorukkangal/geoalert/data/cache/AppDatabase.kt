package com.dorukkangal.geoalert.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dorukkangal.geoalert.data.store.geoalert.GeoAlertDao
import com.dorukkangal.geoalert.data.store.geoalert.model.GeoAlertEntity
import com.dorukkangal.geoalert.data.store.product.ProductDao
import com.dorukkangal.geoalert.data.store.product.model.ProductEntity
import java.util.concurrent.Executors

@Database(
    entities = [GeoAlertEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun geoAlertDao(): GeoAlertDao

    abstract fun productDao(): ProductDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "app-db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            getInstance(context).productDao().insertMock()
                        }
                    }
                })
                .build()
        }
    }
}
