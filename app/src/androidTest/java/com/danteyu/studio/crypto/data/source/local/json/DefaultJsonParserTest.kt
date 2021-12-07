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
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import javax.inject.Named
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TEST_FILE = "test_currency.json"
private const val NONEXISTENT_FILE = "nonexistentFile.json"

/**
 * Created by George Yu in Dec. 2021.
 */
@HiltAndroidTest
class DefaultJsonParserTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val ontology = CurrencyInfo("ONT", "Ontology", "ONT")

    @Inject
    lateinit var moshi: Moshi
    private lateinit var jsonParser: JsonParser

    @Before
    fun setUp() {
        hiltRule.inject()
        jsonParser = DefaultJsonParser(appContext, moshi)
    }

    @Test
    fun parseJsonFile_getCurrencyInfo() {
        val listOfCurrencyInfo = jsonParser.getCurrencyInfoFromAsset(TEST_FILE)

        Truth.assertThat(listOfCurrencyInfo?.size).isEqualTo(4)
        Truth.assertThat(listOfCurrencyInfo?.get(0)).isEqualTo(ontology)
    }

    @Test
    fun parseNonexistentFile_getNull() {
        val listOfCurrencyInfo = jsonParser.getCurrencyInfoFromAsset(NONEXISTENT_FILE)

        Truth.assertThat(listOfCurrencyInfo).isNull()
    }
}
