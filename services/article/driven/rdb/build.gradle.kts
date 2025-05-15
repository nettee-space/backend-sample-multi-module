val articleDomain: String by project
val articleException: String by project
val articleReadModel: String by project
val articleApplication: String by project

plugins {
    id("java-library")
}

dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(articleDomain))
    api(project(articleException))
    api(project(articleReadModel))
    api(project(articleApplication))
    api(project(":jpa-core"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}