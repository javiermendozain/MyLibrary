package com.toast.mylibrary.adaptadores

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.toast.mylibrary.R
import com.toast.mylibrary.otros.toast
import kotlinx.android.synthetic.main.slide_layout.view.*

class slide_layout_adapter(val context:Context,
                           val imageView:Array<Int>,
                           val encabezado:Array<String>,
                           val descripcion:Array<String> ) : PagerAdapter() {



    override fun isViewFromObject(view: View, o: Any): Boolean {
         return view==o as ScrollView
    }

    override fun getCount() = encabezado.size




    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view= LayoutInflater.from(context).inflate(R.layout.slide_layout,container, false)
        view.imageView.setImageResource(imageView[position])
        view.textViewEncabezdo.setText(encabezado[position])
        view.textViewDescripcion.setText(descripcion[position])

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
        container.removeView(o as ScrollView)
    }

}