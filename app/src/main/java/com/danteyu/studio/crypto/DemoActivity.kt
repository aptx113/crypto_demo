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
package com.danteyu.studio.crypto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.danteyu.studio.crypto.databinding.ActivityDemoBinding
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.danteyu.studio.crypto.ui.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding
    private val viewModel by viewModels<DemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityDemoBinding.inflate(layoutInflater).also { setContentView(it.root) }.apply {
                lifecycleOwner = this@DemoActivity
                viewModel = this@DemoActivity.viewModel
            }

        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFrag.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.currencyInfoListFlow
            .onEach {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = CurrencyListFragment()
                fragment.arguments = putDataIntoBundle(it)
                fragmentTransaction.replace(R.id.nav_host_fragment_container, fragment)
                    .setTransition(
                        FragmentTransaction.TRANSIT_FRAGMENT_FADE
                    ).commit()
            }
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleScope)

        viewModel.itemClickFlow
            .onEach {
                Toast.makeText(this, "$it clicked", Toast.LENGTH_SHORT).show()
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleScope)
    }

    private fun putDataIntoBundle(currencyInfoList: List<CurrencyInfo>): Bundle {
        val bundle = Bundle()
        val array = arrayListOf<CurrencyInfo>().apply { addAll(currencyInfoList) }
        bundle.putParcelableArrayList(CURRENCY_KEY, array)
        return bundle
    }
}
