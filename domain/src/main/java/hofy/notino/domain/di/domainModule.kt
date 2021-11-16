package hofy.notino.domain.di

import hofy.notino.domain.usecase.product.ChangeFavouriteProductStatusUseCase
import hofy.notino.domain.usecase.product.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetProductsUseCase(get(), get()) }
    single { ChangeFavouriteProductStatusUseCase(get(), get()) }
}