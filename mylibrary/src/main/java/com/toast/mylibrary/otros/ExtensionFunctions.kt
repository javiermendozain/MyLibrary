package com.toast.mylibrary.otros

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Handler
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import com.airbnb.lottie.LottieAnimationView
import com.toast.mylibrary.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


fun Activity.pantallaCompleta()=getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


fun Activity.isConexionNet(): Boolean {
    try {
        val p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es")
        val data = p.waitFor()
        return data == 0
    } catch (e: Exception) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    return false
}

fun Fragment.isConexionNet(): Boolean {
    try {
        val p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es")
        val data = p.waitFor()
        return data == 0
    } catch (e: Exception) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    return false
}

fun EditText.validate(validation: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            validation(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun Activity.isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun Activity.isValidPassword(password: String): Boolean {
    // Necesita Contener -->    1 Num / 1 Minuscula / 1 Mayuscula / 1 Special / Min Caracteres 6
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}

fun Activity.isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

fun Activity.contexto() = this

fun View.contexto() = this.context

fun Fragment.contexto() = this.context

fun Activity.inflarVista(layout: Int) = LayoutInflater.from(this).inflate(layout, null)

fun Fragment.inflarVista(layout: Int) = LayoutInflater.from(this.context).inflate(layout, null)


fun fechaActual(separador: String? = constante.separador): String {
    val dia: Int
    val mes: Int
    val ano: Int
    val c = Calendar.getInstance()
    val d: String
    val m: String
    dia = c.get(Calendar.DAY_OF_MONTH)
    mes = c.get(Calendar.MONTH) + 1
    ano = c.get(Calendar.YEAR)
    m = (if (mes.toString().length == 1) "0$mes" else mes).toString()
    d = (if (dia.toString().length == 1) "0$dia" else dia).toString()
    return "$ano$separador$m$separador$d"
}

fun formatoFecha(ano: Int, mes: Int, dia: Int, separador: String? = constante.separador): String {
    val d: String
    val m: String
    m = (if (mes.toString().length == 1) "0$mes" else mes).toString()
    d = (if (dia.toString().length == 1) "0$dia" else dia).toString()
    return "$ano$separador$m$separador$d"
}

fun horaActual(): String {
    val hora: Int
    val minutos: Int
    val h: String
    val m: String
    val zona: String

    val c = Calendar.getInstance()
    hora = c.get(Calendar.HOUR_OF_DAY)
    minutos = c.get(Calendar.MINUTE)

    h = (if (hora % 12 == 0) "12" else hora % 12).toString()
    m = (if (minutos.toString().length == 1) "0$minutos" else minutos).toString()
    zona = if (hora >= 12) "pm" else "am"

    return "$h:$m $zona"
}

fun formatoHora(hora: Int, minutos: Int): String {
    val h: String
    val m: String
    val zona: String
    h = (if (hora % 12 == 0) "12" else hora % 12).toString()
    m = (if (minutos.toString().length == 1) "0$minutos" else minutos).toString()
    zona = if (hora >= 12) "pm" else "am"
    return "$h:$m $zona"
}

fun String.toFechaDate():Date{
    val format = SimpleDateFormat(constante.formato_fecha)
    return format.parse(this)
}

fun String.diaSemana(arrayDias: Array<String>): String {
    val format = SimpleDateFormat(constante.formato_fecha)
    val cal = GregorianCalendar()
    val dia: Int
    cal.time = format.parse(this)
    dia = cal.get(Calendar.DAY_OF_WEEK) - 1
    return arrayDias[dia]
}

fun String.mesFecha(arrayMeses: Array<String>, separador: String? = constante.separador) = arrayMeses[this.split(separador!!)[1].toInt() - 1]

fun String.diaFecha(separador: String? = constante.separador) = this.split(separador!!)[2].toInt()

fun String.annoFecha(separador: String? = constante.separador) = this.split(separador!!)[0].toInt()

fun String.maxDiaMes(): Int {
    val format = SimpleDateFormat(constante.formato_fecha)
    val cal = GregorianCalendar()
    cal.time = format.parse(this)
    return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun EditText.escribiendo(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun String.primerCaracter() = this.substring(0, 1)

fun String.ultimoCaracter() = this.substring(this.length - 1, this.length)

fun String.esVacio() = this.isEmpty()

fun EditText.esVacio() = this.text.toString().isEmpty()

fun EditText.texto() = this.text.toString()

fun TextView.texto() = this.text.toString()

fun AutoCompleteTextView.texto() = this.text.toString()

fun Int.isNatural() = this >= 0

// * Activities
fun Activity.snackBar(message: CharSequence, view: View?,
                      duration: Int = Snackbar.LENGTH_SHORT, action: String? = null,
                      actionEvt: (v: View) -> Unit = {}) {
    if (view != null) {
        val snackBar = Snackbar.make(view, message, duration)
        if (!action.isNullOrEmpty()) {
            snackBar.setAction(action, actionEvt)
        }
        snackBar.show()
    }

}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resourceId, duration).show()

fun Activity.dialogoConTiempo(Titulo: String, Mensaje: String? = null, tiempoDelayMilisg: Long = 1000, icon: Int? = null, cancelable: Boolean = true) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(Titulo)
    builder.setMessage(Mensaje)
    builder.setCancelable(cancelable)
    icon?.let { builder.setIcon(icon) }
    val alertDialog = builder.create()
    alertDialog.show()

    val handler = Handler()
    handler.postDelayed({ alertDialog.cancel() }, tiempoDelayMilisg)
}

inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}


// Mirar el curso Kotlin en Udemy
fun Activity.goToActivityResult(action: String, requestCode: Int, init: Intent.() -> Unit = {}) {
    val intent = Intent(action)
    intent.init()
    startActivityForResult(intent, requestCode)
}

//*  Fragments
fun Fragment.dialogoConTiempo(Titulo: String, Mensaje: String? = null, tiempoDelayMilisg: Long = 2000, icon: Int? = null, cancelable: Boolean = true) {
    val builder = AlertDialog.Builder(this.context)
    builder.setTitle(Titulo)
    builder.setMessage(Mensaje)
    builder.setCancelable(cancelable)
    icon?.let { builder.setIcon(icon) }
    val alertDialog = builder.create()
    alertDialog.show()

    val handler = Handler()
    handler.postDelayed({ alertDialog.cancel() }, tiempoDelayMilisg)
}

// Cancelar con .dismiss()
fun Activity.dialogoProceso(textoToMostrar: String): ProgressDialog {
    var progress: ProgressDialog
    progress = ProgressDialog(this)
    progress.setMessage(textoToMostrar)
    progress.show()
    return progress
}
 // Cancelar con .dismiss()
fun Fragment.dialogoProceso(textoToMostrar: String): ProgressDialog {
    var progress: ProgressDialog
    progress = ProgressDialog(this.context)
    progress.setMessage(textoToMostrar)
    progress.show()
    return progress
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this.context, message, duration).show()

fun Fragment.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this.context, resourceId, duration).show()

fun Fragment.snackBar(message: CharSequence, view: View?,
                      duration: Int = Snackbar.LENGTH_SHORT, action: String? = null,
                      actionEvt: (v: View) -> Unit = {}) {
    if (view != null) {
        val snackBar = Snackbar.make(view, message, duration)
        if (!action.isNullOrEmpty()) {
            snackBar.setAction(action, actionEvt)
        }
        snackBar.show()
    }

}

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!


fun mostrarFragment(contenedor: Int, fragmentToMostrar: Fragment, supportFragmentManager: FragmentManager) = supportFragmentManager.beginTransaction().replace(contenedor, fragmentToMostrar).commit()


// ------------------------------------ Descargado, solo .--> Mensajes -------------------------------------

/*
*   Llamar AlerDialog:
*   dialogo(Titulo,Mensaje,{
*        positiveButton("Texto"){Actions}
*        negativeButton("Texto"){Actions}
*   }).show()
* * */
inline fun Activity.dialogo(title: CharSequence? = null, message: CharSequence? = null, func: AlertDialogHelper.() -> Unit): AlertDialog {
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Activity.dialogo(titleResource: Int = 0, messageResource: Int = 0, func: AlertDialogHelper.() -> Unit): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Fragment.dialogo(title: CharSequence? = null, message: CharSequence? = null, func: AlertDialogHelper.() -> Unit): AlertDialog {
    return AlertDialogHelper(context!!, title, message).apply {
        func()
    }.create()
}

inline fun Fragment.dialogo(titleResource: Int = 0, messageResource: Int = 0, func: AlertDialogHelper.() -> Unit): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(context!!, title, message).apply {
        func()
    }.create()
}

@SuppressLint("InflateParams")
class AlertDialogHelper(context: Context, title: CharSequence?, message: CharSequence?) {

    private val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_info, null)
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            .setView(dialogView)

    private val title: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogInfoTitleTextView)
    }

    private val message: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogInfoMessageTextView)
    }

    private val positiveButton: Button by lazy {
        dialogView.findViewById<Button>(R.id.dialogInfoPositiveButton)
    }

    private val negativeButton: Button by lazy {
        dialogView.findViewById<Button>(R.id.dialogInfoNegativeButton)
    }

    private var dialog: AlertDialog? = null

    var cancelable: Boolean = true

    init {
        this.title.text = title
        this.message.text = message
    }

    fun positiveButton(@StringRes textResource: Int, color: Int = R.color.headerLabelColor, func: (() -> Unit)? = null) {
        with(positiveButton) {
            text = builder.context.getString(textResource)
            this.setTextColor(resources.getColor(color))
            setClickListenerToDialogButton(func)
        }
    }

    fun positiveButton(text: CharSequence, color: Int = R.color.headerLabelColor, func: (() -> Unit)? = null) {
        with(positiveButton) {
            this.text = text
            this.setTextColor(resources.getColor(color))
            setClickListenerToDialogButton(func)
        }
    }

    fun negativeButton(@StringRes textResource: Int, color: Int = R.color.headerLabelColor, func: (() -> Unit)? = null) {
        with(negativeButton) {
            text = builder.context.getString(textResource)
            //  this.setTextColor(resources.getColor(color))
            setClickListenerToDialogButton(func)
        }
    }


    fun negativeButton(text: CharSequence, color: Int = R.color.headerLabelColor, func: (() -> Unit)? = null) {
        with(negativeButton) {
            this.text = text
            //    this.setTextColor((color))
            setClickListenerToDialogButton(func)
        }
    }

    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog {
        title.goneIfTextEmpty()
        message.goneIfTextEmpty()
        positiveButton.goneIfTextEmpty()
        negativeButton.goneIfTextEmpty()

        dialog = builder
                .setCancelable(cancelable)
                .create()

        return dialog!!
    }

    private fun TextView.goneIfTextEmpty() {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }

}


// -------------------------------------  AlertDialogo Personalizado

/*-------------- Animacion Lottie----------------
        dialogoAvanzado("Titulo",
          "Mensaje",
          constante.Lottie,
          "nameAnimacion.json",{
                                positiveButton("TextoBottom"){Accion}
                            }).show()

  -------------- Drawable ----------------
        dialogoAvanzado("Titulo",
          "Mensaje",
          constante.Imagen,
          resources.getDrawable(R.drawable.ic_computador),{
                                positiveButton("TextoBottom"){Accion}
                            }).show()
*/
inline fun Activity.dialogoAvanzado(title: CharSequence? = null, message: CharSequence? = null, constLottie_Img: Int=constante.Lottie,recursoToMostrar:Any?=null, func: AlertDialogHelperAvanzado.() -> Unit): AlertDialog {
    return AlertDialogHelperAvanzado(this, title, message,constLottie_Img,recursoToMostrar).apply {
        func()
    }.create()
}

inline fun Activity.dialogoAvanzado(titleResource: Int = 0, messageResource: Int = 0,constLottie_Img: Int=constante.Lottie,recursoToMostrar:Any?=null, func: AlertDialogHelperAvanzado.() -> Unit): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelperAvanzado(this, title, message,constLottie_Img,recursoToMostrar).apply {
        func()
    }.create()
}

inline fun Fragment.dialogoAvanzado(title: CharSequence? = null, message: CharSequence? = null,constLottie_Img: Int=constante.Lottie,recursoToMostrar:Any?=null, func: AlertDialogHelperAvanzado.() -> Unit): AlertDialog {
    return AlertDialogHelperAvanzado(context!!, title, message,constLottie_Img,recursoToMostrar).apply {
        func()
    }.create()
}

inline fun Fragment.dialogoAvanzado(titleResource: Int = 0, messageResource: Int = 0,constLottie_Img: Int=constante.Lottie,recursoToMostrar:Any?=null, func: AlertDialogHelperAvanzado.() -> Unit): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelperAvanzado(context!!, title, message,constLottie_Img,recursoToMostrar).apply {
        func()
    }.create()
}

@SuppressLint("InflateParams")
class AlertDialogHelperAvanzado(context: Context, title: CharSequence?, message: CharSequence?,constLottie_Img: Int,recursoToMostrar:Any?) {

    private val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_info_avanzado, null)
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            .setView(dialogView)

    private val title: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogInfoTitleTextView)
    }

    private val message: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogInfoMessageTextView)
    }

    private val positiveButton: Button by lazy {
        dialogView.findViewById<Button>(R.id.buttonAccion)
    }

    private val lottieView: LottieAnimationView by lazy {
        dialogView.findViewById<LottieAnimationView>(R.id.lottieView)
    }

    private val imageView: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.imageView)
    }

    private var dialog: AlertDialog? = null

    var cancelable: Boolean = true

    init {
        this.title.text = title
        this.message.text = message
        recursoToMostrar?.let {
             // If recursoToMostrar tiene valor
             when(constLottie_Img){
                 constante.Lottie->{
                     lottieView.visibility=View.VISIBLE
                     lottieView.setAnimation(recursoToMostrar as String)
                     lottieView.playAnimation()
                 }
                 constante.Imagen->{
                     imageView.visibility=View.VISIBLE
                     imageView.setImageDrawable(recursoToMostrar as Drawable)
                 }
             }

        }?: run {
            // If recursoToMostrar es null.
            imageView.visibility=View.GONE
            lottieView.visibility=View.GONE

        }


    }

    fun positiveButton(@StringRes textResource: Int, color: Int = R.color.grisClaro, func: (() -> Unit)? = null) {
        with(positiveButton) {
            text = builder.context.getString(textResource)
            this.setTextColor(resources.getColor(color))
            setClickListenerToDialogButton(func)
        }
    }

    fun positiveButton(text: CharSequence, color: Int = R.color.grisClaro, func: (() -> Unit)? = null) {
        with(positiveButton) {
            this.text = text
            this.setTextColor(resources.getColor(color))
            setClickListenerToDialogButton(func)
        }
    }



    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog {
        title.goneIfTextEmpty()
        message.goneIfTextEmpty()
        positiveButton.goneIfTextEmpty()

        dialog = builder
                .setCancelable(cancelable)
                .create()
        dialog!!.window.setBackgroundDrawableResource(R.color.tranparente)
        return dialog!!
    }

    private fun TextView.goneIfTextEmpty() {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }

}

