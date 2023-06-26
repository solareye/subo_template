package com.github.solareye.subotemplate.module.src.model

fun test(
    packageName: String,
) = """
package $packageName.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Test(
    val test: @RawValue Any,
) : Parcelable
""".trimIndent()