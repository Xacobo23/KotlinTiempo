plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.11.0")


    implementation ("org.openjfx:javafx-controls:19.0.2")
    implementation ("org.openjfx:javafx-fxml:19.0.2")

    // Dependencia para JXMapViewer2
    implementation("org.jxmapviewer:jxmapviewer2:2.0")

}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}