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
package com.danteyu.studio.crypto.di

import android.content.Context
import androidx.room.Room
import com.danteyu.studio.crypto.CURRENCY_TABLE
import com.danteyu.studio.crypto.data.source.local.db.CryptoDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by George Yu in Dec. 2021.
 */
@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CryptoDatabase::class.java,
        CURRENCY_TABLE
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: CryptoDatabase) = database.cryptoDao()

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()
}
