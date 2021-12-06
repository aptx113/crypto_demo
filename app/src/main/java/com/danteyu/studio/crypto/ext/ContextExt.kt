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
package com.danteyu.studio.crypto.ext

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import timber.log.Timber
import java.io.IOException

/**
 * Created by George Yu in Dec. 2021.
 */
inline fun <reified T> Context.generateObjectsFromAsset(
    moshi: Moshi,
    fileName: String
): List<T>? {
    val listType = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(listType)

    val jsonObjectResponse: String? = try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (e: IOException) {
        Timber.e("Exception: $e")
        ""
    }
    return jsonObjectResponse?.let { adapter.fromJson(it) }
}
