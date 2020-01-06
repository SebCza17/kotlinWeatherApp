package dao

import com.beust.klaxon.Klaxon
import model.City
import java.net.URL

class WeatherApi(val coreURL: String, val PID: String, val units: String = "metric"){

    fun getCurrentByCity(cityName: String): City? = Klaxon().parse<City>(makeResponse("weather", cityName))

    fun makeResponse(typeURL: String, cityName: String): String = URL(coreURL + typeURL + "?units=" + units + "&q=" + cityName + "&APPID=" + PID).readText()
}