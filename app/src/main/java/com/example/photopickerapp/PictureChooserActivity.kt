package com.example.photopickerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.photopickerapp.ui.theme.PhotoPickerAppTheme

class PictureChooserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoPickerAppTheme {
                PhotoPicker()
            }
        }
    }
}


@Composable
fun PhotoPicker(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var picUri by remember { mutableStateOf<Uri?>(null) }
    val resultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { picUri = it }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            //* means all formats (e.g. PNG, JPEG, ..., etc.)
            onClick = { resultLauncher.launch("image/*") },
        ) {
            Text(text = stringResource(id = R.string.pick_a_photo))

        }
        Image(
            // coil
            painter = rememberAsyncImagePainter(model = picUri, imageLoader = ImageLoader(context)),
            contentDescription = stringResource(id = R.string.photo),
            modifier = modifier.size(250.dp)
        )
        EffectButton(
            effect = "bw",
            picUri = picUri,
            btnText = R.string.black_and_white
        )
        EffectButton(
            effect = "bc",
            picUri = picUri,
            btnText = R.string.brightness_and_contrast
        )
    }
}

@Composable
fun EffectButton(effect: String, picUri: Uri?, @StringRes btnText: Int) {
    val context = LocalContext.current
    Button(onClick = {
        if (picUri != null) {
            val i = Intent(context, ImageEffectActivity::class.java)
            i.putExtra("effect", effect)
            i.putExtra("photo", picUri)
            context.startActivity(i)
        } else
            Toast.makeText(
                context,
                R.string.choose_an_image_before_selecting_an_effect,
                Toast.LENGTH_LONG
            ).show()

    }) {
        Text(text = stringResource(id = btnText))
    }

}

@Preview(showBackground = true)
@Composable
private fun PhotoPickerPreview() {
    PhotoPicker()
}