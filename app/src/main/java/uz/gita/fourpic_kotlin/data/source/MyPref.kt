package uz.gita.fourpic_kotlin.data.source

import android.content.Context
import android.content.SharedPreferences
import uz.gita.fourpic_kotlin.App.App

class MyPref private constructor() {
    val pref: SharedPreferences = App.context.getSharedPreferences("FourPic", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private lateinit var myPref: MyPref

        fun getInstance(): MyPref {
            if (!::myPref.isInitialized) {
                myPref = MyPref()
            }
            return myPref
        }
    }

    fun savePos(pos: Int) {
        editor.putInt("POS", pos).apply()
    }

    fun getPos(): Int = pref.getInt("POS", 0)

    fun saveCoin(coin: Int) {
        editor.putInt("COIN", coin).apply()
    }

    fun saveTrueLetter(letter: String) {
        if (letter == "1") {
            editor.putString("TRUEANS", "").apply()
        }else {
            var s = getTrueLetter()
            s += letter + " "
            editor.putString("TRUEANS", s).apply()
        }
    }

    fun getTrueLetter(): String? {
        return pref.getString("TRUEANS", "")
    }

    fun getCoin(): Int {
        return pref.getInt("COIN", 400)
    }

}