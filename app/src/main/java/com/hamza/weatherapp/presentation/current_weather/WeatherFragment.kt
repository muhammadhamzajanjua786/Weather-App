package com.hamza.weatherapp.presentation.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hamza.weatherapp.R
import com.hamza.weatherapp.common.extension.collectLifecycleFlow
import com.hamza.weatherapp.common.extension.gone
import com.hamza.weatherapp.common.extension.hideKeyboard
import com.hamza.weatherapp.common.extension.onSafeClick
import com.hamza.weatherapp.common.extension.showKeyboard
import com.hamza.weatherapp.common.extension.showToast
import com.hamza.weatherapp.common.extension.visible
import com.hamza.weatherapp.common.extension.visibleOrInvisible
import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.round

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeWeatherState()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.includeSearch.inputFindCityWeather.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val cityName = binding.includeSearch.inputFindCityWeather.text.toString().trim()
                if (cityName.isNotEmpty()) {
                    hideSearchView()
                    viewModel.getCurrentWeather(cityName)
                }
                true
            } else {
                false
            }
        }
    }

    private fun observeWeatherState() {
        collectLifecycleFlow(viewModel.weather) { state ->
            when {
                state.error.isNotEmpty() -> showToast(state.error)
                state.data != null -> bindWeatherData(state.data)
            }
        }
    }

    private fun setupClickListeners() {
        binding.includeSearch.btnSearch.onSafeClick {
            showSearchView()
        }
        binding.includeSearch.btnCancel.onSafeClick {
            hideSearchView()
        }

        binding.btnNextForecast.onSafeClick {
            navigateToForecast()
        }
    }

    private fun showSearchView() {
        binding.includeSearch.apply {
            searchView.visibleOrInvisible()
            btnSearch.gone()
            inputFindCityWeather.showKeyboard()
        }
    }

    private fun hideSearchView() {
        binding.includeSearch.apply {
            searchView.visibleOrInvisible()
            btnSearch.visible()
            inputFindCityWeather.hideKeyboard()
            inputFindCityWeather.text?.clear()
        }
    }

    private fun bindWeatherData(weather: WeatherData) {
        binding.run {
            layoutWeather.visible()
            layoutSearchACity.gone()
            tvCityName.text = weather.cityName
            val roundedTemperature = round(weather.temp).toInt()
            tvTemperature.text = roundedTemperature.toString()
            tvWeatherCondition.text = weather.condition
            tvTempHighLow.text = with(weather) {
                val minTemp = round(weather.tempMin).toInt()
                val maxTemp = round(weather.tempMax).toInt()
                "High: $maxTemp°   Low: $minTemp°"
            }
            tvHumidity.text = getString(R.string.humidity, weather.humidity)
            tvWind.text = getString(R.string.wind_speed, weather.windSpeed)
            tvVisibility.text =
                getString(R.string.visibility_text, weather.visibilityInKilometers())
        }
    }

    private fun navigateToForecast() {
        findNavController().navigate(
            WeatherFragmentDirections.navigateToForecastFragment(
                cityName = viewModel.getCityName()
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}