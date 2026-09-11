package com.example.lostfoundapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lostfoundapp.model.ItemType
import com.example.lostfoundapp.model.LostItem
import com.example.lostfoundapp.viewmodel.LostFoundViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: LostFoundViewModel,
    onAddItem: () -> Unit
){
    val items by viewModel.items.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lost & Found",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00013F)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItem) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Recent Items",
                fontSize = 24.sp,
                color = Color(0xFF00013F)
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (items.isEmpty()) {
                Text(
                    text = "No items yet"
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items) { item ->
                        ItemCard(
                            item = item,
                            onResolve = { viewModel.resolveItem(item.id) },
                            onDelete = { viewModel.deleteItem(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    item: LostItem,
    onResolve: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (item.type == ItemType.LOST) {
                        "🔴 LOST"
                    } else {
                        "🟢 FOUND"
                    },
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = item.location,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                if (!item.isResolved) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onResolve,
                            colors = androidx.compose.material3.ButtonDefaults
                                .buttonColors(
                                    containerColor = Color(0xFF00013F),
                                    contentColor = Color.White
                                )
                        ) {
                            Text("Resolve")
                        }
                        Button(
                            onClick = onDelete,
                            colors = androidx.compose.material3.ButtonDefaults
                                .buttonColors(
                                    containerColor = Color(0xFF00013F),
                                    contentColor = Color.White
                                )
                        ) {
                            Text("Delete")
                        }
                    }
                } else {
                    Text(
                        text = "✓ Resolved",
                        color = Color.Green
                    )
                }
            }
            item.imageUri?.let { imageUri ->
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Item image",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .height(120.dp)
                        .width(120.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}