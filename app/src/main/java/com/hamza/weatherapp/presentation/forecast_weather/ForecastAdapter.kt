package com.hamza.weatherapp.presentation.forecast_weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamza.weatherapp.R
import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.databinding.ItemWeatherForecastBinding
import com.hamza.weatherapp.domain.util.DateUtils
import java.lang.ref.WeakReference
import kotlin.math.round

class ForecastAdapter(private val _context: Context) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val list = mutableListOf<WeatherData>()
    private val context = WeakReference(_context).get()

    inner class ForecastViewHolder(private val binding: ItemWeatherForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherData) {
            binding.apply {
                tvDay.text = DateUtils.getDayName(weather.date ?: "")
                tvWeatherCondition.text = weather.condition
                tvTempHighLow.text = with(weather) {
                    val minTemp = round(tempMin).toInt()
                    val maxTemp = round(tempMax).toInt()
                    "H: $maxTemp° L:$minTemp°"
                }
                if (context != null) {
                    tvHumidity.text = context.getString(R.string.humidity, weather.humidity)
                    tvWind.text = context.getString(R.string.wind_speed, weather.windSpeed)
                    tvPressure.text = context.getString(R.string.pressure, weather.windSpeed)
                    tvChanceOfRain.text = context.getString(R.string.chance_of_rain, weather.chanceOfRain())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ItemWeatherForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(data: List<WeatherData>) {
        val oldSize = list.size - 1
        list.addAll(data)
        notifyItemRangeChanged(oldSize, list.size)
    }
}