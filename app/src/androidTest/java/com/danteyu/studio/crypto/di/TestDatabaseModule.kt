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
import com.danteyu.studio.crypto.data.source.local.db.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by George Yu in Dec. 2021.
 */
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
object TestDatabaseModule {

    @Singleton
    @Provides
    @Named("testDatabase")
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context
    ) =
        Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java)
            .allowMainThreadQueries().build()
}