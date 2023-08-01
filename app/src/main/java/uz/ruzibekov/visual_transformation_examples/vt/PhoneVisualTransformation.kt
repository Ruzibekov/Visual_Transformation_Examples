package uz.ruzibekov.visual_transformation_examples.vt

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.getFormattedTextPhone()
        return TransformedText(formattedText, offsetMapping)
    }

    private val offsetMapping = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int { // 998901144147

            /*  textField-da kiritilgan VT ishlatilmagan ma'lumotda cursor indexlarida nechta o'zgarish bo'lishi kerakligi hisoblanadi
             *  va offset qiymatiga shu son qo'shib, qaytariladi.
             *  Misol:
             *  offset = 0, hech qanday ma'lumot mavjud bo'lmaganida. bu holatda offsetning o'zi yoki nol soni qaytariladi. Chunki hech narsa yozilmagan TextField-da VT ishlatmaymiz.
             *  offset = 1 va 3 oralig'i, bu holatda kiritilgan son oldidan "+" belgisi qo'yilganligi sababli, offsetga 1 soni qo'shib qaytariladi
             *  offset = 3 va 5 oralig'i == 99890 soni kiritilganida. Bu holatda 2ta VT mavjud bo'ladi "+" belgisi va bo'sh joy. +998 90. Shu sababdan offset-ga 2soni qo'shib qaytariladi.
             */

            return when (offset) { //offset = textField-da kiritilgan o'zgarishsiz ma'lumotdagi cursorning indeksi
                in 1..3 -> offset + 1
                in 3..5 -> offset + 2
                in 6..8 -> offset + 3
                in 9..10 -> offset + 4
                in 11..12 -> offset + 5
                else -> 0
            }
        }

        /*  TextField-da qay
        *
        * */
        override fun transformedToOriginal(offset: Int): Int { // +998 90 114-41-47
            return when (offset) { // offset = textField-da kiritilgan ma'lumotning VT ishlatilgandagi cursorning indeksi
                in 1..4 -> offset - 1
                in 5..7 -> offset - 2
                in 8..11 -> offset - 3
                in 12..14 -> offset - 4
                in 15..17 -> offset - 5
                else -> 0
            }
        }
    }

    private fun AnnotatedString.getFormattedTextPhone(): AnnotatedString { //+998 90 114-41-47
        val text = this.text

        return AnnotatedString(
            buildString {

                if (text.isNotEmpty()) append('+')

                if (text.length in 1..3)  //998
                    append(text)
                else if (text.length > 3) {
                    append(text.substring(0, 3))
                    append(" ")
                }

                if (text.length in 4..5) //99890
                    append(text.substring(3))
                else if (text.length > 5) {  //998901
                    append(text.substring(3, 5))
                    append(" ")
                }

                if (text.length in 6..8) //998 90 123
                    append(text.substring(5))
                else if (text.length > 8) {
                    append(text.substring(5, 8)) //998 90 123 3
                    append("-")
                }

                if (text.length in 9..10) //998 90 123 23
                    append(text.substring(8))
                else if (text.length > 10) { //998 90 123 23 2
                    append(text.substring(8, 10))
                    append("-")
                    append(text.substring(10))
                }
            })
    }


}

