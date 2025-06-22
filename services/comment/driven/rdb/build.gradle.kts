dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(":comment:comment-api"))
    api(project(":comment:comment-application"))
    api(project(":jpa-core"))

    // for using json column
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}