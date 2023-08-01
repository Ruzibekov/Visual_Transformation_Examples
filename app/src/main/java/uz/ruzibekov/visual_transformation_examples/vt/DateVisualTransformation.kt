package uz.ruzibekov.visual_transformation_examples.vt

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.getFormattedText()
        return TransformedText(formattedText, offsetMapping)
    }

    private fun AnnotatedString.getFormattedText(): AnnotatedString {
        return AnnotatedString(buildString {

            if (text.length in 0..2) append(text)
            else if (text.length > 2) {
                append(text.substring(0, 2))
                append('/')
            }

            if (text.length in 3..4) append(text.substring(2))
            else if (text.length > 4) {
                append(text.substring(2, 4))
                append('/')
                append(text.substring(4))
            }
        })
    }

    private val offsetMapping = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int { //   28092003
            return when (offset) {
                in 0..2 -> offset
                in 3..4 -> offset + 1
                in 5..8 -> offset + 2
                else -> 0
            }
        }

        override fun transformedToOriginal(offset: Int): Int { //   28/09/2003
            return when (offset) {
                in 0..2 -> offset
                in 3..5 -> offset - 1
                in 5..10 -> offset - 2
                else -> 0
            }
        }
    }
}