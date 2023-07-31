package uz.ruzibekov.visual_transformation_examples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ruzibekov.visual_transformation_examples.ui.theme.Visual_Transformation_ExamplesTheme
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

        var data by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = data,
                onValueChange = { data = it },
                visualTransformation = PhoneVisualTransformation()
            )
        }
    }

}