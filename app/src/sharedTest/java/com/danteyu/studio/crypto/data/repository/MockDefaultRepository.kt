package com.danteyu.studio.crypto.data.repository

import com.danteyu.studio.crypto.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by George Yu in Dec. 2021.
 */
class MockDefaultRepository : Repository {

    var currencyInfoListFlow: Flow<List<CurrencyInfo>>? = null

    override suspend fun parseJsonAndGetAll(fileName: String): Flow<List<CurrencyInfo>>? =
        currencyInfoListFlow
}
