package com.hamza.weatherapp.common.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Fragment.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}

fun Fragment.navigate(action: NavDirections) = findNavController().navigate(action)

fun Fragment.popUp() = findNavController().popBackStack()