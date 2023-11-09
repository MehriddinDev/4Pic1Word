package uz.gita.fourpic_kotlin.mvp.contracts

import uz.gita.fourpic_kotlin.data.model.QuestionData

interface MainContract {
    interface Model{
        fun getImagesToView():List<Int>
        fun getPos():Int
    }

    interface Presenter{
        fun clickBtnPlay()
        fun clickBtnAbout()

        fun loadView()
    }

    interface View{
        fun openPlayActivity()
        fun openAboutActivity()

        fun setImageToView(images:List<Int>)
        fun setPosToView(pos:Int)
    }
}