package com.github.solareye.subotemplate.module

fun manifestModule(
    packageName: String,
) = """
<?xml version="1.0" encoding="utf-8"?>
<manifest package="$packageName" />
"""