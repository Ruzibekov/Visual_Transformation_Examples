package uz.ruzibekov.visual_transformation_examples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.ruzibekov.visual_transformation_examples.vt.DateVisualTransformation
import uz.ruzibekov.visual_transformation_examples.vt.PhoneVisualTransformation

object MainContentView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Default() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = mainTopBar()
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Content()
            }
        }
    }

    @Composable
    fun mainTopBar(): @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var phone by remember { mutableStateOf("") }


            TextField(
                value = phone,
                onValueChange = {
                    if (it.length < 13) phone = it
                },
                visualTransformation = PhoneVisualTransformation(),
                maxLines = 1,
                label = labelView(textRes = R.string.label_phone_number)
            )

            Spacer(modifier = Modifier.height(10.dp))

            var date by remember { mutableStateOf("") }

            TextField(
                value = date,
                onValueChange = {
                    if (it.length < 9) date = it
                },
                visualTransformation = DateVisualTransformation(),
                maxLines = 1,
                label = labelView(textRes = R.string.label_date_of_birth)
            )

            Spacer(modifier = Modifier.height(10.dp))


        }

    }

    @Composable
    fun labelView(textRes: Int): @Composable () -> Unit = {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }

}