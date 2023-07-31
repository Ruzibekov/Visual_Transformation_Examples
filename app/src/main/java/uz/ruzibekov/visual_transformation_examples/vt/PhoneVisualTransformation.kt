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
            if (offset in 1..3) return offset + 1
            if (offset in 3..5) return offset + 2
            if (offset in 6..8) return offset + 3
            if (offset in 9..10) return offset + 4
            if (offset in 11..12) return offset + 5
            return 0
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
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

