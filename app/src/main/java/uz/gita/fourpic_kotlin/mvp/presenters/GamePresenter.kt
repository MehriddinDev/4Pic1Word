package uz.gita.fourpic_kotlin.mvp.presenters

import android.util.Log
import android.view.View
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.fourpic_kotlin.App.App
import uz.gita.fourpic_kotlin.mvp.contracts.GameContract
import uz.gita.fourpic_kotlin.mvp.models.GameModel
import uz.gita.fourpic_kotlin.mvp.views.GameActivity

class GamePresenter(private val view: GameActivity) : GameContract.Presenter {
    private val model = GameModel()
    private lateinit var typedVariants: ArrayList<Boolean>
    private lateinit var typedAnswers: ArrayList<String>
    var n: Int = 0

    override fun loadQuestion() {
        setQuestion()
        setVisibilityToAnswer()
        setImageToView()
        initArrayLists()
        view.setCoinToView(model.getCoin())
        view.setColorToAnswers()
        view.setTrueAnswerFirstTime(model.getTrueAnswer())
        setEnabledFirstTime()
    }

    override fun loadNextQuestion() {
        view.setEnabledAllBtn()
        view.setAllTextToAnswer()
        view.setAllVisibleToAnswer()
        view.setColorToAnswers()
        setQuestion()
        setVisibilityToAnswer()
        setImageToView()
        initArrayLists()
        model.saveTrueAnswer("1")
    }

    private fun setEnabledFirstTime(){
        val trueAns = model.getTrueAnswer()

        if (trueAns != null && trueAns != "") {
            for (i in 0 until trueAns.length){
                val pos = getPosVariantByLetter(trueAns[i].toString())
                if (pos != -1){
                    view.setEnabledToVariant(pos,false)
                    typedVariants[pos] = false
                    typedAnswers.set(checktypedAnswersPos(trueAns[i].toString()),trueAns[i].toString())
                }
            }
        }
    }

    override fun variantBtnClick(posVariant: Int, letter: String) {
        val posAnswer = checkAnswer()
        if (posAnswer != -1) {
            view.setAnswer(posAnswer, letter)
            view.setEnabledToVariant(posVariant, false)

            typedAnswers.set(posAnswer, letter)
            typedVariants.set(posVariant, false)


            if (!isWin() && typedAnswers[typedAnswers.size - 1] != "0") {
                view.showYoYoAnimation()
                Log.d("III","showYoYoAnimation")
            }

        }
    }

    override fun answerBtnClick(posAnswer_: Int, letter: String) {
        val posAnswer = posAnswer_ - n
        val variant = model.getVariant()

        if (letter != "0")
            for (i in 0..13) {
                if (variant[i].toString() == letter && !typedVariants[i]) {
                    view.setAnswer(posAnswer, "")
                    view.setEnabledToVariant(i, true)

                    typedVariants.set(i, true)
                    typedAnswers.set(posAnswer, "0")
                    return
                }
            }
    }

    override fun deleteHelpBtnClick() {
        val variant = model.getVariant()
        val answer = model.getAnswer()
        var current = 0
        if(model.getCoin() < 60){
            Toast.makeText(App.context, "You don't hava enough coin !", Toast.LENGTH_SHORT).show()
        }
        for (i in 0 until variant.length) {
            val pos = checktypedAnswersPos(variant[i].toString())
            Toast.makeText(App.context, "$pos letter = $variant   ${variant[i]}", Toast.LENGTH_SHORT).show()

            if (pos != -1 && typedVariants[i] && current != 0) {
                typedVariants.set(i,false)
                typedAnswers.set(pos,"0")

                view.setAnswer(pos,variant[i].toString())
                view.setEnabledToVariant(i,false)

                model.saveCoin(model.getCoin() - 60)
                view.setCoinToView(model.getCoin())
                current++
            }
        }
    }

    private fun checktypedAnswersPos(letter: String):Int{
        val answer = model.getAnswer()
        for (i in 0 until answer.length) {
            if (answer[i].toString() == letter/* && typedAnswers[i] != letter*/) {
                return i
            }
        }
        return -1
    }

    override fun addHelpBtnClick() {
        if (model.getCoin() < 60) {
            Toast.makeText(App.context, "You don't hava enough coin!", Toast.LENGTH_SHORT).show()
        } else {
            val answer = model.getAnswer()
            val sizeAns = answer.length
            for (i in 0 until sizeAns) {
                val letter = answer[i].toString()
                val pos = getPosVariantByLetter(letter)
                if (typedAnswers[i] == "0" && pos != -1) {

                    typedAnswers[i] = letter
                    typedVariants[pos] = false

                    model.saveTrueAnswer(answer[i].toString())

                    view.setAnswer(i,answer[i].toString())
                    view.setTrueAnswer(i, answer[i].toString())
                    view.setEnabledToVariant(pos, false)

                    isWin()

                    model.saveCoin(model.getCoin() - 60)
                    view.setCoinToView(model.getCoin())
                    return
                }
            }
        }
    }

    override fun getAnswer(): String {
        return model.getAnswer()
    }

    private fun getPosVariantByLetter(letter: String): Int {
        val variant = model.getVariant()
        for (i in 0 until variant.length) {
            if (variant[i].toString() == letter && typedVariants[i]) {
                return i
            }
        }
        return -1
    }

    private fun setQuestion() {
        view.setVariant(model.getVariant())
    }

    private fun setVisibilityToAnswer() {
        n = 8 - model.getAnswer().length
        view.setVisibilityToAnswer(n)
    }

    private fun setImageToView() {
        view.setImageToView(model.getImageByPos())
    }

    private fun initArrayLists() {
        val ansSize = model.getAnswer().length

        typedAnswers = ArrayList(ansSize)
        typedVariants = ArrayList(14)

        for (i in 0 until 14) {
            typedVariants.add(true)
        }

        for (i in 0 until ansSize) {
            typedAnswers.add("0")
        }
    }

    private fun checkAnswer(): Int {
        for (i in 0 until typedAnswers.size) {
            if (typedAnswers[i].equals("0")) {
                return i
            }
        }
        return -1
    }

    private fun isWin(): Boolean {
        val ans = model.getAnswer()
        for (i in 0 until typedAnswers.size) {
            if (typedAnswers[i] != ans[i].toString()) {
                return false
            }
        }

        if (model.getPos() == 20) {
            view.showEndDialog()
            model.savePos(20)
        } else {
            view.showNextDialog(model.getAnswer())
            model.savePos(model.getPos() + 1)
            model.saveCoin(model.getCoin() + 8)
            view.setCoinToView(model.getCoin())
        }
        return true
    }

}