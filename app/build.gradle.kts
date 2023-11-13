plugins {
    id("kotlin.coroutine.study.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":jwhy"))
    implementation(project(":ezhoon"))
    implementation(project(":jaeeun"))
}

application {
    mainClass.set("kotlin.coroutine.study.app.AppKt")
}
