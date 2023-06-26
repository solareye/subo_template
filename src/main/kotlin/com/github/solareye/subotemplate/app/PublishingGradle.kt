package com.github.solareye.subotemplate.app

fun publishingGradle(
    featureName: String,
    applicationId: String,
) = """
    apply plugin: 'maven-publish'

    afterEvaluate {
        publishing {
            publications {
                release(MavenPublication) {
                    from components.release
                    artifactId = '$featureName'
                    groupId = '$applicationId'
                    version = deployment.version

                    pom {
                        name = '$featureName'
                        description = 'SMB $featureName subo module'
                        url = ''
                        scm {
                            connection = ''
                            developerConnection = ''
                            url = ''
                        }
                    }
                }
            }
            repositories {
                maven {
                    name = "nexus-ci"
                    url = "https://nexus-ci.corp.dev.vtb/repository/pfom-maven-lib/"
                    credentials(PasswordCredentials) {
                        username System.getenv("SMB_PUBLISHING_USERNAME")
                        password System.getenv("SMB_PUBLISHING_PASSWORD")
                    }
                }
            }
        }
    }
""".trimIndent()