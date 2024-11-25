package org.example

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URI
import java.nio.charset.Charset

fun prediccionMunicipioHoras(id : Int) : WeatherDataHoras{
    val urlDatos = URI(AccesoApi().prediccionMunicipiosHoras(id).datos).toURL().readText(Charset.forName("ISO-8859-1"))
    val gson = Gson()
    val jsonArray = JsonParser.parseString(urlDatos).asJsonArray
    return gson.fromJson(jsonArray.get(0), WeatherDataHoras::class.java)
}

data class WeatherDataHoras(
    val origen: Origin,
    val elaborado: String,
    val nombre: String,
    val provincia: String,
    val prediccion: Prediction
)

data class Origin(
    val productor: String,
    val web: String,
    val enlace: String,
    val language: String,
    val copyright: String,
    val notaLegal: String
)

data class Prediction(
    val dia: List<DayPrediction>
)

data class DayPrediction(
    val estadoCielo: List<SkyState>,
    val precipitacion: List<ValuePeriod>,
    val probPrecipitacion: List<ValuePeriod>,
    val probTormenta: List<ValuePeriod>,
    val nieve: List<ValuePeriod>,
    val probNieve: List<ValuePeriod>,
    val temperatura: List<ValuePeriod>,
    val sensTermica: List<ValuePeriod>,
    val humedadRelativa: List<ValuePeriod>,
    val vientoAndRachaMax: List<WindRachaMax>,
    val fecha: String,
    val orto: String,
    val ocaso: String
)

data class SkyState(
    val value: String,
    val periodo: String,
    val descripcion: String
)

data class ValuePeriod(
    val value: String,
    val periodo: String
)

data class WindRachaMax(
    val direccion: List<String>? = null, // Optional in case only value is present
    val velocidad: List<String>? = null,
    val value: String? = null, // Optional in case only direction and speed are present
    val periodo: String
)
