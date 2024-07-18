package com.example.photopickerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photopickerapp.ui.theme.PhotoPickerAppTheme

class PictureChooserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoPickerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    PhotoPicker()
                }
            }
        }
    }
}

@Composable
fun PhotoPicker(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = { },
        ) {
            Text(text = stringResource(id = R.string.pick_a_photo))

        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.photo),
            modifier = modifier.size(250.dp)
        )
        Button(
            onClick = { },
        ) {
            Text(text = stringResource(id = R.string.black_and_white))

        }
        Button(
            onClick = { },
        ) {
            Text(text = stringResource(id = R.string.brightness_and_contrast))

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoPickerPreview() {
    PhotoPicker()
}