package uz.ruzibekov.visual_transformation_examples.vt

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

//class DateVisualTransformation : VisualTransformation {
//
//    override fun filter(text: AnnotatedString): TransformedText {
//        val formattedText = text.text.getFormattedText()
//        return TransformedText(formattedText, offsetMapping)
//    }
//
//    private fun String.getFormattedText(): AnnotatedString {
//
//    }
//
//    private val offsetMapping = object : OffsetMapping {
//
//        override fun originalToTransformed(offset: Int): Int {
//
//        }
//
//        override fun transformedToOriginal(offset: Int): Int {
//            TODO("Not yet implemented")
//        }
//    }
//}