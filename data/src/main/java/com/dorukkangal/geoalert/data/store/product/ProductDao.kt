package com.dorukkangal.geoalert.data.store.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dorukkangal.geoalert.data.store.product.model.ProductEntity
import io.reactivex.Flowable

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun findAll(): Flowable<List<ProductEntity>>

    @Insert
    fun insertMock(product: List<ProductEntity> = mock())

    fun mock(): List<ProductEntity> =
        arrayListOf(
            ProductEntity(id = 1, name = "Coca Cola", description = "Hayatın Tadı"),
            ProductEntity(id = 2, name = "Efes Pilsen", description = "Soğuk İçiniz"),
            ProductEntity(id = 3, name = "Tuborg", description = "Bira bu kapağın altındadır")
        )
}
