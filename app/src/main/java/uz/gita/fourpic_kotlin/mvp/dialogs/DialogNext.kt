package uz.gita.fourpic_kotlin.mvp.dialogs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import nl.dionsegijn.konfetti.KonfettiView
import uz.gita.fourpic_kotlin.R

class DialogNext(private val context:Context):AlertDialog(context) {
    lateinit var next: Button
    lateinit var containerAnswers:LinearLayout
    private lateinit var answers: ArrayList<TextView>
    private var ansCount:Int = 0
    private lateinit var listener:DialogListener
    var konfetti: KonfettiView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_win_next)
        konfetti = KonfettiView(context)
        findView()
        next.setOnClickListener{
            listener.onClick()
            dismiss()
        }
        setCancelable(false)
        window?.setBackgroundDrawable(null)
    }

    fun showKonfettiAnimation() {
        konfetti?.build()
            ?.addColors(Color.YELLOW, Color.GREEN, Color.RED, Color.MAGENTA)
            ?.setDirection(0.0, 359.0)
            ?.setSpeed(8f, 10f)
            ?.setFadeOutEnabled(true)
            ?.setTimeToLive(3000L)
            ?.addShapes(nl.dionsegijn.konfetti.models.Shape.CIRCLE)
            ?.addSizes(nl.dionsegijn.konfetti.models.Size(12))
            ?.setPosition(50f, konfetti!!.width + 50f, 50f, 80f)
            ?.streamFor(600, 3000L)
    }

    private fun findView(){
        next = findViewById(R.id.btnMenu)!!
        containerAnswers = findViewById(R.id.answers)!!
        answers = ArrayList(8)
        for (i in 0..7) {
            val btn: TextView = containerAnswers.getChildAt(i) as TextView
            btn.setTag(i)
            answers.add(btn)
        }
        konfetti = findViewById(R.id.konfettiView);
    }

    fun setVisibilityToAns(n: Int){
        ansCount = 8-n
        for (i in 0 until ansCount){
            answers[i].visibility = View.GONE
        }
    }

    fun setTextToAns(ans:String){
         for (i in 0 until ans.length){
             answers[i+ansCount].text = ans[i].toString()
         }
    }

    interface DialogListener{
        fun onClick()
    }

    fun setDialoglistener(listener_:DialogListener){
        listener = listener_
    }
}