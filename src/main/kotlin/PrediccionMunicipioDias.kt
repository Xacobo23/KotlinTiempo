package org.example

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URI


fun PrediccionMunicipioDias(id : Int) : WeatherData{
    val urlDatos = URI(AccesoApi().PrediccionMunicipiosDias(id).datos).toURL().readText()
    val gson = Gson()
    val jsonArray = JsonParser.parseString(urlDatos).asJsonArray
    return gson.fromJson(jsonArray.get(0), WeatherData::class.java)
}


data class WeatherData(
    val origen: Origen,
    val elaborado: String,
    val nombre: String,
    val provincia: String,
    val prediccion: Prediccion,
    val id: Int,
    val version: Double
)

data class Origen(
    val productor: String,
    val web: String,
    val enlace: String,
    val language: String,
    val copyright: String,
    val notaLegal: String
)

data class Prediccion(
    val dia: List<Dia>
)

data class Dia(
    val probPrecipitacion: List<PeriodoValue>,
    val cotaNieveProv: List<PeriodoValue>,
    val estadoCielo: List<StateCielo>,
    val viento: List<Viento>,
    val rachaMax: List<PeriodoValue>,
    val temperatura: Temperatura,
    val sensTermica: SensTermica,
    val humedadRelativa: HumedadRelativa,
    val uvMax: Int?,
    val fecha: String
)

data class PeriodoValue(
    val value: String?,
    val periodo: String? = null
)

data class StateCielo(
    val value: String?,
    val periodo: String? = null,
    val descripcion: String?
)

data class Viento(
    val direccion: String?,
    val velocidad: Int,
    val periodo: String? = null
)

data class Temperatura(
    val maxima: Int,
    val minima: Int,
    val dato: List<HoraValue>
)

data class SensTermica(
    val maxima: Int,
    val minima: Int,
    val dato: List<HoraValue>
)

data class HumedadRelativa(
    val maxima: Int,
    val minima: Int,
    val dato: List<HoraValue>
)

data class HoraValue(
    val value: Int,
    val hora: Int
)
