import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.github.player13"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.postgresql:postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor:reactor-net:2.0.8.RELEASE") // 2.0.5.RELEASE
    implementation("io.projectreactor:reactor-core:3.4.24") // 2.0.5.RELEASE
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.24")

    implementation("io.netty:netty-all") // 4.0.33.Final

    implementation("org.webjars:webjars-locator:0.45")
    implementation("org.webjars:sockjs-client:1.5.1") // 1.0.2
    implementation("org.webjars:stomp-websocket:2.3.4") // 2.3.3

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<BootJar> {
    archiveClassifier.set("boot")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
