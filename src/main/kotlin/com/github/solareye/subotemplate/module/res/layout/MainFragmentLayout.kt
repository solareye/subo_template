package com.github.solareye.subotemplate.module.res.layout

fun mainFragmentLayout(
    featureNamePrefix: String,
) = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <ru.vtb.smb.ui_kit.toolbar.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:title="@string/${featureNamePrefix}_main_screen_title"
        app:navigationIcon="@drawable/ds_ic_24_ni_arrow_back_tail" />

</androidx.constraintlayout.widget.ConstraintLayout>
"""