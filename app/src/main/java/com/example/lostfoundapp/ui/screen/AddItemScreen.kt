package com.example.lostfoundapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lostfoundapp.model.ItemType
import com.example.lostfoundapp.viewmodel.LostFoundViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen (
    viewModel: LostFoundViewModel,
    onItemAdded: () -> Unit
){
    var name by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(ItemType.LOST) }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            selectedImageUri = it
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Item",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00013F)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Item name") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF00013F),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF00013F),
                    cursorColor = Color(0xFF00013F)
                )
            )
            Text("Type")
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row {
                    RadioButton(
                        selected = selectedType == ItemType.LOST,
                        onClick = { selectedType = ItemType.LOST },
                        colors = androidx.compose.material3.RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF00013F)
                        )
                    )
                    Text(
                        text = "Lost",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
                Row {
                    RadioButton(
                        selected = selectedType == ItemType.FOUND,
                        onClick = { selectedType = ItemType.FOUND },
                        colors = androidx.compose.material3.RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF00013F)
                        )
                    )
                    Text(
                        text = "Found",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Location") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF00013F),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF00013F),
                    cursorColor = Color(0xFF00013F)
                )
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF00013F),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF00013F),
                    cursorColor = Color(0xFF00013F)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    imagePickerLauncher.launch(arrayOf("image/*"))
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00013F),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Choose Image")
            }
            selectedImageUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.addItem(
                        name = name,
                        type = selectedType,
                        description = description,
                        location = location,
                        imageUri = selectedImageUri?.toString()
                    )
                    onItemAdded()
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00013F),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && location.isNotBlank()
            ) {
                Text("Add Item")
            }
        }
    }
}
