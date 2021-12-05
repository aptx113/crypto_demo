package com.danteyu.studio.crypto.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danteyu.studio.crypto.databinding.FragListCurrencyBinding

/**
 * Created by George Yu in Dec. 2021.
 */
class CurrencyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragListCurrencyBinding.inflate(layoutInflater, container, false).root
}
