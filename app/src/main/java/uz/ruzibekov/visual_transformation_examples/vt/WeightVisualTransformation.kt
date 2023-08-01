package uz.ruzibekov.visual_transformation_examples.vt

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class WeightVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.getFormattedText()),
            offsetMapping = offsetMapping
        )
    }
}

private fun AnnotatedString.getFormattedText(): String {
    return if (text.isNotEmpty())
        "$text kg"
    else ""
}

private val offsetMapping = object : OffsetMapping {

    //ma'lumot kiritilganida "kg" yozuvidan keyingi sakrashimiz shart emasligi sababli shunchaki offset qaytariladi
    override fun originalToTransformed(offset: Int): Int { //   60
        return offset
    }

    // TextField focus qilinganida, "kg" yozuviga to'g'ri keladigan index bosilganida, -3 orqali, kursor 3ta indeks orqaga sakraydi
    override fun transformedToOriginal(offset: Int): Int { //   60 kg
        return if(offset > 3) offset -3
        else offset
    }
}