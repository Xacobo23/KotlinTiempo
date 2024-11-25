import com.google.gson.*
import java.lang.reflect.Type
import java.time.format.DateTimeFormatter
import org.example.AccesoApi
import java.net.URI
import java.nio.charset.Charset
import java.time.LocalDateTime

fun prediccionMaritimaCostera(id: Int): PrediccionMaritimaCostera {
    val urlDatos = URI(AccesoApi().prediccionMaritimaCostera(id).datos).toURL().readText(Charset.forName("ISO-8859-1"))

    val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()

    // Convertir la respuesta JSON a un JsonArray
    val jsonArray = JsonParser.parseString(urlDatos).asJsonArray

    // Deserializar el primer objeto del array a Boletin
    return gson.fromJson(jsonArray.get(0), PrediccionMaritimaCostera::class.java)
}


data class PrediccionMaritimaCostera(
    val origen: Origen,
    val aviso: Aviso,
    val situacion: Situacion,
    val prediccion: Prediccion,
    val tendencia: Tendencia,
    val id: String,
    val nombre: String
)

data class Origen(
    val productor: String,
    val web: String,
    val language: String,
    val copyright: String,
    val notaLegal: String,
    val elaborado: LocalDateTime,
    val inicio: LocalDateTime,
    val fin: LocalDateTime
)

data class Aviso(
    val inicio: LocalDateTime,
    val fin: LocalDateTime,
    val texto: String,
    val id: String,
    val nombre: String
)

data class Situacion(
    val analisis: LocalDateTime,
    val inicio: LocalDateTime,
    val fin: LocalDateTime,
    val texto: String,
    val id: String,
    val nombre: String
)

data class Subzona(
    val texto: String,
    val id: Int,
    val nombre: String
)

data class Zona(
    val id: Int,
    val nombre: String,
    val subzona: List<Subzona>
)

data class Prediccion(
    val inicio: LocalDateTime,
    val fin: LocalDateTime,
    val zona: List<Zona>
)

data class Tendencia(
    val inicio: LocalDateTime,
    val fin: LocalDateTime,
    val texto: String
)

// Adaptador para manejar LocalDateTime con Gson
class LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun serialize(src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.format(formatter))
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime.parse(json.asString, formatter)
    }
}




