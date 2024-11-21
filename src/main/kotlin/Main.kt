package org.example

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.internal.bind.TypeAdapters.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI
import java.net.URL

fun main() {

    

    val url = URI("https://www.el-tiempo.net/api/json/v2/provincias/27").toURL().readText()

    var gson = Gson()
    val jsonObject = JsonParser.parseString(url).asJsonObject
    val provinciaJson = jsonObject.getAsJsonObject("provincia")
    val ciudadesJsonArray = jsonObject.getAsJsonArray("ciudades")
    val ciudades = gson.fromJson(ciudadesJsonArray, Array<Ciudad>::class.java).toList()
    val provincia = gson.fromJson(provinciaJson, Provincia::class.java)


    var a = ListaProvincias().getListaProvincias().find { it.CODPROV=="27"}

    print(a)
}

data class Provincia(
    val CODPROV: String,
    val NOMBRE_PROVINCIA: String,
    val CODAUTON: String,
    val COMUNIDAD_CIUDAD_AUTONOMA: String,
    val CAPITAL_PROVINCIA: String


) {
    override fun toString(): String {
        return """
            
            
            CODPROV='$CODPROV',
            NOMBRE_PROVINCIA='$NOMBRE_PROVINCIA',
            CODAUTON='$CODAUTON',
            COMUNIDAD_CIUDAD_AUTONOMA='$COMUNIDAD_CIUDAD_AUTONOMA',
            CAPITAL_PROVINCIA='$CAPITAL_PROVINCIA'
           
           
                """.trimIndent()
    }
}

data class Ciudad(
    val id: String,
    val idProvince: String,
    val name: String,
    val nameProvince: String,
    val stateSky: EstadoCielo,
    val temperatures: Temperaturas

) {
    override fun toString(): String {
        return """
 id='$id',
 idProvince='$idProvince', 
 name='$name', 
 nameProvince='$nameProvince', 
 stateSky=$stateSky, 
 temperatures=$temperatures)
             
"""
    }
}

data class EstadoCielo(
    val description: String,
    val id: String,
)

data class Temperaturas(
    val max: String,
    val min: String,
)