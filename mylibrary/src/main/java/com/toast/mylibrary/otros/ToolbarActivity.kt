package com.toast.mylibrary.otros

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.toast.mylibrary.interfacess.IToolbar

open class ToolbarActivity : AppCompatActivity(), IToolbar {

    protected var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar?, title: String?) {
        _toolbar = toolbar
        _toolbar?.let {setSupportActionBar(_toolbar);  setTitleBar(title); }
    }

    override fun enableHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun setTitleBar(title: String? ) {
        title?.let { supportActionBar?.setTitle(title) }

    }

    override fun onCreate(savedInstanceState: Bundle?)=super.onCreate(savedInstanceState)

}
