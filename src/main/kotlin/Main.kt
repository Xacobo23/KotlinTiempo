import org.example.AccesoApi
import org.example.prediccionMunicipioDias
import org.example.prediccionMunicipioHoras


fun main() {

//    println(prediccionMunicipioDias(27049).nombre.formatoNombre())
// println(prediccionMunicipioDias(27048).prediccion.dia[0].viento)
//    println(prediccionMunicipioHoras(27048).prediccion.dia[2].estadoCielo[1].value)

//    println(AccesoApi().mapaAnalisis().metadatos)

//    println(infoMunicipio(27049).capital)

//    val a = infoAllMunicipios().listaMunicipios
//    println(a.size)
//    println(a[48])

//    val a =prediccionMaritimaCostera(40).prediccion.zona[0].subzona[0].texto
//    println(a)

    println(prediccionMunicipioHoras(15078).prediccion.dia[1].precipitacion)



}

fun String.formatoNombre(): String{
    val textoModificado = if (this.contains(",")) {
        val (primero, segundo) = this.split(",").map { it.trim() }
        "$segundo $primero"
    } else {
        this
    }
    return textoModificado
}