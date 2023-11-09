package uz.gita.fourpic_kotlin.domain

import uz.gita.fourpic_kotlin.R
import uz.gita.fourpic_kotlin.data.model.QuestionData
import uz.gita.fourpic_kotlin.data.source.MyPref
import java.util.*
import kotlin.collections.ArrayList

class Repasitory {
    private lateinit var questions: List<QuestionData>
    val pref = MyPref.getInstance()

    companion object {
        private lateinit var repasitory: Repasitory

        fun getInstance(): Repasitory {
            if (!::repasitory.isInitialized) {
                repasitory = Repasitory()
            }
            return repasitory
        }
    }

    init {

        loadQuestion()
    }

    fun getImageByPos(): List<Int> {
        return questions[getPos()].images
    }

    fun getVariant():String{
         return questions[getPos()].variants
    }

    fun getAnswer():String{
        return questions[getPos()].answer
    }

    private fun loadQuestion() {
        questions = ArrayList()

        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic1_1,
                    R.drawable.pic1_2,
                    R.drawable.pic1_3,
                    R.drawable.pic1_4
                ), "STUDY", "DSFTRUHOLBKMYX"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic2_1,
                    R.drawable.pic2_2,
                    R.drawable.pic2_3,
                    R.drawable.pic2_4
                ), "APPLE", "AEODLPUOLPRMCX"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic3_1,
                    R.drawable.pic3_3,
                    R.drawable.pic3_3,
                    R.drawable.pic3_4
                ), "SWIM", "CESDWGUILBRMCX"
            )
        )

        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic4_1,
                    R.drawable.pic4_2,
                    R.drawable.pic4_3,
                    R.drawable.pic4_4
                ), "work", "perauhwkhlodbn"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic5_1,
                    R.drawable.pic5_2,
                    R.drawable.pic5_3,
                    R.drawable.pic5_4
                ), "phone", "hwoaunpghledwr"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic6_1,
                    R.drawable.pic6_2,
                    R.drawable.pic6_3,
                    R.drawable.pic6_4
                ), "football", "owtabhpfolelqt"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic7_1,
                    R.drawable.pic7_2,
                    R.drawable.pic7_3,
                    R.drawable.pic7_4
                ), "season", "potauspnhsedvu"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic8_1,
                    R.drawable.pic8_2,
                    R.drawable.pic8_3,
                    R.drawable.pic8_4
                ), "lamp", "pwmauhpghlpdyu"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic9_1,
                    R.drawable.pic9_2,
                    R.drawable.pic9_3,
                    R.drawable.pic9_4
                ), "angry", "awtauhygnlrdaw"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic10_1,
                    R.drawable.pic10_2,
                    R.drawable.pic10_3,
                    R.drawable.pic10_4
                ), "america", "mwkarhpihleacr"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic11_1,
                    R.drawable.pic11_2,
                    R.drawable.pic11_3,
                    R.drawable.pic11_4
                ), "computer", "umtachpeorepgt"
            )
        )

        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic13_1,
                    R.drawable.pic13_2,
                    R.drawable.pic13_3,
                    R.drawable.pic13_4
                ), "home", "hmtauopghledmv"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic14_1,
                    R.drawable.pic14_2,
                    R.drawable.pic14_3,
                    R.drawable.pic14_4
                ), "letter", "rwtautpehledxv"
            )
        )

        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.circus_1,
                    R.drawable.circus_2,
                    R.drawable.circus_3,
                    R.drawable.circus_4
                ), "CIRCUS", "CERDLPUOISRMCX"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.clown_1,
                    R.drawable.clown_2,
                    R.drawable.clown_3,
                    R.drawable.clown_4
                ), "CLOWN", "CESNOGUILBRWAX"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic15_1,
                    R.drawable.pic15_2,
                    R.drawable.pic15_3,
                    R.drawable.pic15_4
                ), "smile", "sitaretlhmidzf"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic16_1,
                    R.drawable.pic16_2,
                    R.drawable.pic16_3,
                    R.drawable.pic16_4
                ), "plane", "abnauemlhpidsf"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic17_1,
                    R.drawable.pic17_2,
                    R.drawable.pic17_3,
                    R.drawable.pic17_4
                ), "voice", "gvcaueolhsidft"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic18_1,
                    R.drawable.pic18_2,
                    R.drawable.pic18_3,
                    R.drawable.pic18_4
                ), "car", "yatacemlrsidty"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic19_1,
                    R.drawable.pic19_2,
                    R.drawable.pic19_3,
                    R.drawable.pic19_4
                ), "moon", "dimaneolhsodur"
            )
        )
        (questions as ArrayList<QuestionData>).add(
            QuestionData(
                Arrays.asList(
                    R.drawable.pic20_1,
                    R.drawable.pic20_2,
                    R.drawable.pic20_3,
                    R.drawable.pic20_4
                ), "student", "gfdtunelhstbud"
            )
        )

    }

    fun savePos(pos:Int){
        pref.savePos(pos)
    }

    fun getPos():Int{
        return pref.getPos()
    }

    fun saveCoin(coin:Int){
        pref.saveCoin(coin)
    }

    fun getCoin():Int{
        return pref.getCoin()
    }

    fun saveTrueLetter(letter:String){
        pref.saveTrueLetter(letter)
    }

    fun getTrueLetter(): String? {
        return pref.getTrueLetter()
    }

}