dependencies {
    val bom = dependencyManagement.importedProperties

    api(project(":board:api"))
    api(project(":jpa-core"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${bom["querydsl.version"]}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${bom["querydsl.version"]}:jakarta")
}