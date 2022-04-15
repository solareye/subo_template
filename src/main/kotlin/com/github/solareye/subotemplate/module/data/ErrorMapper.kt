package com.github.solareye.subotemplate.module.data

fun errorMapper(
    packageName: String,
) = """
package $packageName.data
import ru.vtb.smb.subo_common.data.BaseErrorMapper

object ErrorMapper : BaseErrorMapper()

""".trimIndent()