package uz.gita.fourpic_kotlin.mvp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import uz.gita.fourpic_kotlin.R

class AboutActivity : AppCompatActivity() {
    lateinit var btnBack:AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        btnBack = findViewById(R.id.backBtn)
        btnBack.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}