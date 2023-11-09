package uz.gita.fourpic_kotlin.mvp.contracts

import android.widget.TextView

interface GameContract {

    interface Model {
        fun getImageByPos(): List<Int>
        fun getVariant(): String
        fun getAnswer(): String
        fun savePos(pos:Int)
        fun getPos():Int
        fun saveCoin(coin:Int)
        fun getCoin():Int
        fun getTrueAnswer():String?
        fun saveTrueAnswer(letter:String)
    }

    interface Presenter {
        fun loadQuestion()
        fun loadNextQuestion()

        fun variantBtnClick(posVariant: Int,letter:String)
        fun answerBtnClick(posVariant: Int,letter:String)

        fun deleteHelpBtnClick()
        fun addHelpBtnClick()

        fun getAnswer():String
    }

    interface View {
        fun setImageToView(images: List<Int>)
        fun setVariant(varint: String)
        fun setAnswer(pos:Int, letter:String)
        fun setTrueAnswer(pos: Int, letter: String)
        fun setTrueAnswerFirstTime(letters:String?)
        fun setColorToAnswers()

        fun setEnabledToVariant(pos:Int, logic:Boolean)
        fun setCoinToView(coin:Int)
        fun setVisibilityToAnswer(length:Int)
        fun setAllVisibleToAnswer()
        fun showNextDialog(answer:String)
        fun showEndDialog()
        fun showYoYoAnimation()
    }
}