package com.hamza.weatherapp.presentation.forecast_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hamza.weatherapp.R
import com.hamza.weatherapp.common.extension.collectLifecycleFlow
import com.hamza.weatherapp.databinding.FragmentForecastBinding
import com.hamza.weatherapp.presentation.current_weather.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val forecastAdapter by lazy { ForecastAdapter(requireContext()) }
    private val viewModel by viewModels<ForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
        setUpRecyclerView()
        observeForecastState()
    }

    private fun setupUI() {
        binding.toolbar.setTitle(viewModel.cityName.value.plus(" Forecast"))
    }

    private fun setupClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeForecastState() {
        collectLifecycleFlow(viewModel.forecast) { state ->
            forecastAdapter.updateList(state.data ?: emptyList())
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = forecastAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}