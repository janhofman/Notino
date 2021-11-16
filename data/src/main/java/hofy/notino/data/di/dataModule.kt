package hofy.notino.data.di

import androidx.room.Room
import hofy.notino.data.BuildConfig
import hofy.notino.data.db.NotinoDb
import hofy.notino.data.db.datasource.AddFavouriteProductsDataSource
import hofy.notino.data.db.datasource.FavouriteProductIdsDataSource
import hofy.notino.data.db.datasource.ProductsDataSource
import hofy.notino.data.db.datasource.RemoveFavouriteProductDataSource
import hofy.notino.data.remote.NotinoApi
import hofy.notino.domain.datasource.IAddFavouriteProductDataSource
import hofy.notino.domain.datasource.IFavouriteProductIdsDataSource
import hofy.notino.domain.datasource.IProductsDataSource
import hofy.notino.domain.datasource.IRemoveFavouriteProduct
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            NotinoDb::class.java, "database-notino"
        ).build()
    }
    single<IProductsDataSource> { ProductsDataSource(get()) }
    single<IFavouriteProductIdsDataSource> { FavouriteProductIdsDataSource(get()) }
    single<IAddFavouriteProductDataSource> { AddFavouriteProductsDataSource(get()) }
    single<IRemoveFavouriteProduct> { RemoveFavouriteProductDataSource(get()) }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(NotinoApi::class.java)
    }
}