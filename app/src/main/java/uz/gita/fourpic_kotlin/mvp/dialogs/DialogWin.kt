package uz.gita.fourpic_kotlin.mvp.dialogs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import nl.dionsegijn.konfetti.KonfettiView
import uz.gita.fourpic_kotlin.App.App
import uz.gita.fourpic_kotlin.R
import uz.gita.fourpic_kotlin.mvp.views.MainActivity

class DialogWin(context: Context) : AlertDialog(context) {
    lateinit var btnMenu:AppCompatButton
    var konfetti: KonfettiView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_win,null,false)
        super.onCreate(savedInstanceState)
        setContentView(view)

        setCancelable(false)
        window?.setBackgroundDrawable(null)


        btnMenu = findViewById(R.id.Menu)!!
        btnMenu.setOnClickListener({
            val i = Intent(context,MainActivity::class.java)
            context.startActivity(i)
            dismiss()
        })

        konfetti = findViewById(R.id.konfettiView);

    }

    fun showKonfettiAnimation() {
        konfetti?.build()
            ?.addColors(Color.YELLOW, Color.GREEN, Color.RED, Color.MAGENTA)
            ?.setDirection(50.0, 359.0)
            ?.setSpeed(10f, 10f)
            ?.setFadeOutEnabled(true)
            ?.setTimeToLive(3000L)
            ?.addShapes(nl.dionsegijn.konfetti.models.Shape.CIRCLE)
            ?.addSizes(nl.dionsegijn.konfetti.models.Size(12))
            ?.setPosition(50f, konfetti!!.width + 50f, 50f, 80f)
            ?.streamFor(600, 3000L)
    }
}