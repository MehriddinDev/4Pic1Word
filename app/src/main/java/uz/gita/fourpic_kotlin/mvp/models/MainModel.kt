package uz.gita.fourpic_kotlin.mvp.models

import uz.gita.fourpic_kotlin.domain.Repasitory
import uz.gita.fourpic_kotlin.mvp.contracts.MainContract

class MainModel:MainContract.Model {
    val repasitory = Repasitory.getInstance()

    override fun getImagesToView(): List<Int> {
        return repasitory.getImageByPos()
    }

    override fun getPos(): Int {
        return repasitory.getPos()
    }
}