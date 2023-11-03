plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1") // Specify the JUnit version
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation("com.itextpdf:kernel:8.0.1")
    implementation("com.itextpdf:io:8.0.1")
    implementation("com.itextpdf:layout:8.0.1")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation ("org.springframework:spring-core:5.3.10.RELEASE")
}

tasks.test {
    useJUnitPlatform()
}