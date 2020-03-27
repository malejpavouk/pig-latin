tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            element = "BUNDLE"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}

// JacocoReport -> JacocoVerify -> build
// first we need to generate report, then fail
tasks.named("build").configure {
    dependsOn(tasks.withType<JacocoCoverageVerification>())
}

tasks.withType<JacocoCoverageVerification> {
    dependsOn(tasks.withType<JacocoReport>())
}

tasks.withType<Checkstyle> {
    // based on google checks, relaxed a bit (+spaces indent)
    configFile = file("${rootDir}/quality/checkstyle.xml")
}

tasks.withType<Pmd> {
    isConsoleOutput = true
    ruleSets = emptyList<String>()
    ruleSetConfig = resources.text.fromFile("quality/pmd.xml")
}

// run PMD just on main
extensions.getByType(PmdExtension::class.java).sourceSets = mutableListOf(the<SourceSetContainer>()["main"])

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("file.encoding", "UTF-8") // force encoding for default streams
}
