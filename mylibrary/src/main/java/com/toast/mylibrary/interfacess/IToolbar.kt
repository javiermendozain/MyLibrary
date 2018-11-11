package com.toast.mylibrary.interfacess

import android.support.v7.widget.Toolbar

interface IToolbar {
    fun toolbarToLoad(toolbar: Toolbar?,title: String?=null)
    fun enableHomeDisplay(value: Boolean)
    fun setTitleBar(title: String?)

}