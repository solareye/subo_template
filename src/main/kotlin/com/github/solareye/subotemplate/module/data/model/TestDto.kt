package com.github.solareye.subotemplate.module.data.model

fun testDto(
    packageName: String,
) = """
package $packageName.data.model

import com.google.gson.annotations.SerializedName

data class TestDto(
    @SerializedName("key")
    val value: Any?
)

""".trimIndent()