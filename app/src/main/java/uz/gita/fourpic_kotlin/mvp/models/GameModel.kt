package uz.gita.fourpic_kotlin.mvp.models

import uz.gita.fourpic_kotlin.domain.Repasitory
import uz.gita.fourpic_kotlin.mvp.contracts.GameContract

class GameModel:GameContract.Model {
    private val repasitory = Repasitory.getInstance()

    override fun getImageByPos():List<Int> {
        return repasitory.getImageByPos()
    }

    override fun getVariant(): String {
        return repasitory.getVariant()
    }

    override fun getAnswer(): String {
        return repasitory.getAnswer()
    }

    override fun savePos(pos:Int) {
        repasitory.savePos(pos)
    }

    override fun getPos(): Int {
        return repasitory.getPos()
    }

    override fun saveCoin(coin:Int) {
        repasitory.saveCoin(coin)
    }

    override fun getCoin(): Int {
        return repasitory.getCoin()
    }

    override fun getTrueAnswer(): String? {
        return repasitory.getTrueLetter()
    }

    override fun saveTrueAnswer(letter: String) {
        repasitory.saveTrueLetter(letter)
    }


}