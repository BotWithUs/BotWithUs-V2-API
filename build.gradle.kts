plugins {
    `maven-publish`
}

group = "net.botwithus.api"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":imgui"))
    implementation("org.xerial:sqlite-jdbc:3.47.0.0")
    implementation("org.apache.commons:commons-compress:1.27.1")

    //test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}