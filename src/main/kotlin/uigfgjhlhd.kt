package org.example

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type
import java.net.URI
import java.net.URL

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(){

    val tipo = object : TypeToken<List<Concello>>() {}.type

    val gson = GsonBuilder()
        .registerTypeAdapter(tipo::class.java, ProvinciaDeserializer())
        .create()

    // Leer el archivo JSON
    val a = File("C:\\Users\\a23xacobobc\\Desktop\\DAM_UF1_Proyecto\\concellos.json").readText()

    // Deserializar el JSON a una lista de provincias
    val provincias: List<Concello> = gson.fromJson(a, tipo)

    // Imprimir los nombres de las provincias y los concellos
    provincias.sortedBy { it.concello }.forEach { provincia ->
        if (provincia.id.toString().take(2) == "27")
            println(provincia.concello)
    }

}


data class Concello(
    val id: Int,
    val concello: String
)

class ProvinciaDeserializer : JsonDeserializer<List<Concello>> {
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): List<Concello> {

        var listaConcellos : MutableList<Concello> = mutableListOf()
        val jsonObject = jsonElement.asJsonArray

        jsonObject.forEach{
            val obj = it.asJsonObject
            val id = obj.get("id").asInt
            val concello = obj.get("concello").asString

            listaConcellos.add(Concello(id, concello))
        }

        return listaConcellos
    }
}
