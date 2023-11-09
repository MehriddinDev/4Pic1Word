package uz.gita.fourpic_kotlin.mvp.views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.ParticleSystem
import uz.gita.fourpic_kotlin.App.App
import uz.gita.fourpic_kotlin.R
import uz.gita.fourpic_kotlin.mvp.contracts.GameContract
import uz.gita.fourpic_kotlin.mvp.dialogs.DialogNext
import uz.gita.fourpic_kotlin.mvp.dialogs.DialogWin
import uz.gita.fourpic_kotlin.mvp.presenters.GamePresenter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.log

class GameActivity : AppCompatActivity(), GameContract.View {

    private var mLastClickTime: Long = 0

    private lateinit var containerVariants: RelativeLayout
    private lateinit var containerAnswers: LinearLayout
    private lateinit var imagesQuestions: ArrayList<ImageView>
    private lateinit var btnBack: AppCompatImageView
    private lateinit var coin: TextView

    /* private lateinit var deleteHelp: ImageView*/
    private lateinit var addhelp: ImageView
    private lateinit var presenter: GamePresenter
    private lateinit var variants: ArrayList<TextView>
    private lateinit var answers: ArrayList<TextView>
    private var ignoreBtnCount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        findView()
        presenter = GamePresenter(this)
        presenter.loadQuestion()

        clickEvents()


        // write here for setTrue answer first time

    }

    override fun setImageToView(imgs: List<Int>) {
        for (i in 0 until 4) {
            imagesQuestions[i].setImageResource(imgs[i])
        }
    }

    override fun setVariant(variant: String) {
        for (i in 0..13) {
            variants[i].text = (variant[i].toString())
        }
    }

    override fun setAnswer(pos: Int, letter: String) {
        answers[pos + ignoreBtnCount].setText(letter)
    }

    override fun setTrueAnswer(pos: Int, letter: String) {
        answers[pos + ignoreBtnCount].setTextColor(Color.parseColor("#3DF501"))
        answers[pos + ignoreBtnCount].isEnabled = false
    }

    override fun setTrueAnswerFirstTime(letters: String?) {

        val answer: String = presenter.getAnswer()
        Log.d("PPP", "yordam = $letters   answer = $answer")
        val str = letters?.trim()
        val arr = str?.split(" ")?.toTypedArray()
        if (arr != null && arr[0] != "") {

            for (i in 0 until arr.size) {
                for (j in 0 until answer.length) {
                    Log.d(
                        "PPP",
                        "arr = ${arr[i]}  answer = ${answer[j]}   anwer = $answer  ${
                            arr[i].equals(answer[j].toString())
                        } "
                    )
                    if (arr[i].equals(answer[j].toString())) {
                        Log.d("PPP", "kirdi")
                        answers[j + ignoreBtnCount].setText(arr[i])
                        answers[j + ignoreBtnCount].setTextColor(Color.parseColor("#3DF501"))
                        answers[j + ignoreBtnCount].isEnabled = false
                    }
                }
            }

        }
    }

    override fun setColorToAnswers() {
        for (i in 0..7) {
            answers[i].setTextColor(Color.parseColor("#FFFFFFFF"))
        }
    }

    override fun setEnabledToVariant(pos: Int, logic: Boolean) {
        variants[pos].isEnabled = logic
    }

    override fun setCoinToView(coin_: Int) {
        coin.text = coin_.toString()
    }

    override fun setVisibilityToAnswer(length: Int) {
        ignoreBtnCount = length
        for (i in 0 until length) {
            answers[i].visibility = View.GONE
        }
    }

    override fun setAllVisibleToAnswer() {
        for (i in 0..7) {
            answers[i].visibility = View.VISIBLE
        }
    }

    override fun showNextDialog(answer: String) {
        val dialog = DialogNext(this)

        /*  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
          dialog.window?.setLayout(
              WindowManager.LayoutParams.MATCH_PARENT,
              WindowManager.LayoutParams.MATCH_PARENT
          )*/
        dialog.show()
        dialog.showKonfettiAnimation()
        dialog.setVisibilityToAns(answer.length)
        dialog.setTextToAns(answer)

        dialog.setDialoglistener(object : DialogNext.DialogListener {
            override fun onClick() {
                presenter.loadNextQuestion()
                dialog.konfetti?.reset()
            }
        })
    }

    override fun showEndDialog() {
        val dialog = DialogWin(this)
        /*dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)*/
        dialog.show()
        dialog.showKonfettiAnimation()
    }


    private fun findView() {
        btnBack = findViewById(R.id.btnBack)
        coin = findViewById(R.id.coin)
        /* deleteHelp = findViewById(R.id.delete)*/
        addhelp = findViewById(R.id.add)
        containerVariants = findViewById(R.id.variants)
        containerAnswers = findViewById(R.id.answers)

        imagesQuestions = ArrayList()

        imagesQuestions.add(findViewById(R.id.image1))
        imagesQuestions.add(findViewById(R.id.image2))
        imagesQuestions.add(findViewById(R.id.image3))
        imagesQuestions.add(findViewById(R.id.image4))

        answers = ArrayList(8)
        for (i in 0..7) {
            val btn: TextView = containerAnswers.getChildAt(i) as TextView
            btn.setTag(i)
            answers.add(btn)
        }

        variants = ArrayList(14)
        for (i in 0..13) {
            val btn: TextView = containerVariants.getChildAt(i) as TextView
            btn.setTag(i)
            variants.add(btn)
        }


    }

    private fun clickEvents() {
        for (i in 0..13) {
            variants[i].setOnClickListener { it ->
                val view: TextView = it as TextView
                var pos = view.getTag() as Int
                val letter = view.text.toString()
                presenter.variantBtnClick(pos, letter)
            }
        }

        for (i in 0..7) {
            answers[i].setOnClickListener {
                val btn: TextView = it as TextView
                val pos = btn.getTag() as Int
                val letter = btn.text.toString()
                presenter.answerBtnClick(pos, letter)
            }
        }

        btnBack.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        addhelp.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()

            presenter.addHelpBtnClick()
        }
    }

    fun setEnabledAllBtn() {
        for (i in 0..13) {
            variants[i].isEnabled = true
        }

        for (i in 0..7) {
            answers[i].isEnabled = true
        }
    }

    override fun showYoYoAnimation() {
        // animation
        YoYo.with(Techniques.Shake)
            .duration(700)
            .repeat(0)
            .playOn(findViewById(R.id.answers))
    }
    fun setAllTextToAnswer() {
        for (i in 0 until answers.size) {
            answers[i].text = ""
        }
        ignoreBtnCount = 0;
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}