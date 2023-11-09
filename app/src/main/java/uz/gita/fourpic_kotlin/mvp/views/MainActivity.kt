package uz.gita.fourpic_kotlin.mvp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import uz.gita.fourpic_kotlin.R
import uz.gita.fourpic_kotlin.mvp.contracts.MainContract
import uz.gita.fourpic_kotlin.mvp.presenters.MainPresenter

class MainActivity : MainContract.View, AppCompatActivity() {

    private lateinit var btnPlay: AppCompatButton
    private lateinit var btnAbout: AppCompatButton
    private lateinit var level: AppCompatTextView
    private lateinit var questionsImg: MutableList<ImageView>
    private lateinit var presenter: MainPresenter
    private var mLastClickTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findView()

        presenter = MainPresenter(this)
        presenter.loadView()
    }

    private fun findView() {
        btnPlay = findViewById(R.id.buttonPlay)
        btnAbout = findViewById(R.id.buttonAbout)
        level = findViewById(R.id.level)

        questionsImg = ArrayList()

        questionsImg.add(findViewById(R.id.image1))
        questionsImg.add(findViewById(R.id.image2))
        questionsImg.add(findViewById(R.id.image3))
        questionsImg.add(findViewById(R.id.image4))

        clickEvents()
    }

    private fun clickEvents() {

        btnPlay.setOnClickListener {
            if(SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()

            presenter.clickBtnPlay()
        }

        btnAbout.setOnClickListener {
            if(SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return@setOnClickListener
            }

            mLastClickTime = SystemClock.elapsedRealtime()
            presenter.clickBtnAbout()
        }
    }

    override fun openPlayActivity() {


        val i = Intent(this,GameActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun openAboutActivity() {

        val i = Intent(this,AboutActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun setImageToView(images:List<Int>) {
        for(i in 0 until 4)
            questionsImg.get(i).setImageResource(images.get(i))
    }

    override fun setPosToView(pos : Int) {
        level.setText(pos.toString())
    }

}