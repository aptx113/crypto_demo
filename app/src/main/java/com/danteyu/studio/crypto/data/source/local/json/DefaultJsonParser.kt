/*
 * Copyright 2021 Dante Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danteyu.studio.crypto.data.source.local.json

import android.content.Context
import com.danteyu.studio.crypto.ext.generateObjectsFromAsset
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by George Yu in Dec. 2021.
 */
class DefaultJsonParser @Inject constructor(
    @ApplicationContext val context: Context,
    private val moshi: Moshi
) :
    JsonParser {

    override fun getCurrencyInfoFromAsset(fileName: String): List<CurrencyInfo>? {
        return context.generateObjectsFromAsset(moshi, fileName)
    }
}
