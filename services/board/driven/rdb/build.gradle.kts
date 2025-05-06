dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(":board:board-api"))
    api(project(":board:board-application"))
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