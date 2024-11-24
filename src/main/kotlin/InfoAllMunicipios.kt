import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.example.AccesoApi
import java.net.URI
import java.nio.charset.Charset

fun infoAllMunicipios():InfoAllMunicipios{
    val urlDatos = URI(AccesoApi().infoAllMunicipios().datos).toURL().readText(Charset.forName("ISO-8859-1"))
    val gson = Gson()

    val listType = object : TypeToken<List<InfoMunicipio>>() {}.type
    val listaMunicipios = gson.fromJson<List<InfoMunicipio>>(urlDatos, listType)

    // Retornar el objeto InfoAllMunicipios que contiene la lista de municipios
    return InfoAllMunicipios(listaMunicipios)
}


data class InfoAllMunicipios (
    val listaMunicipios: List<InfoMunicipio>
)