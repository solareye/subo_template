package com.github.solareye.subotemplate.module.model

fun test(
    packageName: String,
) = """
package $packageName.model

@Parcelize
data class Test(
    val test: Any,
) : Parcelable
""".trimIndent()