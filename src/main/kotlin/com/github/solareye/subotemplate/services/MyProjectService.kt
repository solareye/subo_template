package com.github.solareye.subotemplate.services

import com.intellij.openapi.project.Project
import com.github.solareye.subotemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
