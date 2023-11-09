package uz.gita.fourpic_kotlin.mvp.presenters

import uz.gita.fourpic_kotlin.mvp.contracts.MainContract
import uz.gita.fourpic_kotlin.mvp.models.MainModel
import uz.gita.fourpic_kotlin.mvp.views.MainActivity

class MainPresenter(private val view:MainActivity):MainContract.Presenter {
    val model = MainModel()

    override fun loadView(){
        setImagesToView()
        setPosToView()

    }

    override fun clickBtnPlay() {
        view.openPlayActivity()
    }

    override fun clickBtnAbout() {
        view.openAboutActivity()
    }

    private fun setImagesToView() {
        view.setImageToView(model.getImagesToView())
    }

    private fun setPosToView(){
        view.setPosToView(model.getPos()+1)
    }

}