import com.google.gson.Gson
import com.google.gson.JsonParser
import org.example.AccesoApi
import org.example.WeatherDataHoras
import java.net.URI
import java.nio.charset.Charset

fun infoMunicipio(id: Int):InfoMunicipio{
    val urlDatos = URI(AccesoApi().infoMunicipio(id).datos).toURL().readText(Charset.forName("ISO-8859-1"))
    val gson = Gson()
    val jsonArray = JsonParser.parseString(urlDatos).asJsonArray
    return gson.fromJson(jsonArray.get(0), InfoMunicipio::class.java)
}

data class InfoMunicipio(
    val latitud: String,
    val idOld: String,
    val url: String,
    val latitudDec: String,
    val altitud: String,
    val capital: String,
    val numHab: String,
    val zonaComarcal: String,
    val destacada: String,
    val nombre: String,
    val longitudDec: String,
    val id: String,
    val longitud: String
)
