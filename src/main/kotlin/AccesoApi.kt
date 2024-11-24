package org.example

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URI
import java.time.LocalDate

class AccesoApi {
    private val API_KEY = "/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhMjN4YWNvYm9iY0BpZXNzYW5jbGVtZW50ZS5uZXQiLCJqdGkiOiJlZGM4NTA2Mi0wZjkzLTRlYjgtYWI3OS0xNTQ0NmMyNmQxYzYiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTczMjE4MzY4NCwidXNlcklkIjoiZWRjODUwNjItMGY5My00ZWI4LWFiNzktMTU0NDZjMjZkMWM2Iiwicm9sZSI6IiJ9.krqPkNR6qpQAjA8orycm4AvCaL228I9RzSaaW-y57Q8"

    private fun getApi(url:String):Acceso{
        val urlJson = URI(url).toURL().readText()
        val gson = Gson()
        val jsonObject = JsonParser.parseString(urlJson).asJsonObject
        return gson.fromJson(jsonObject, Acceso::class.java)
    }

    fun prediccionMunicipiosHoras(id:Int):Acceso{
        val url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/horaria/".plus(id)
        return getApi(url.plus(API_KEY))
    }

    fun prediccionMunicipiosDias(id:Int):Acceso{
        val url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/".plus(id)
        return getApi(url.plus(API_KEY))
    }

    fun infoMunicipio(id:Int):Acceso{
        val url= "https://opendata.aemet.es/opendata/api/maestro/municipio/id".plus(id)
        return getApi(url.plus(API_KEY))
    }

    fun infoAllMunicipios():Acceso{
        val url = "https://opendata.aemet.es/opendata/api/maestro/municipios"
        return getApi(url.plus(API_KEY))
    }

    fun mapaAnalisis():Acceso{
        val url = "https://opendata.aemet.es/opendata/api/mapasygraficos/analisis"
        return getApi(url.plus(API_KEY))
    }

    fun prediccionMaritimaCostera(id: Int):Acceso{
        val url = "https://opendata.aemet.es/opendata/api/prediccion/maritima/costera/costa/".plus(id)
        return getApi(url.plus(API_KEY))
    }

    fun CheckApi(acceso: Acceso): Boolean {
        return acceso.estado==200
    }
}

data class Acceso(
    val descripcion: String,
    val estado: Int,
    val datos: String,
    val metadatos: String
)