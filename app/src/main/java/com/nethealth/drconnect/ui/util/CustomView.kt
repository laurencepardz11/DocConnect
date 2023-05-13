package com.nethealth.drconnect.ui.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.nethealth.drconnect.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedField(text: MutableState<String>, holder: String, onChanged: (String) -> Unit) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onChanged.invoke(it)
        },
        placeholder = {
            Text(text = holder, color = colorResource(R.color.placeholder_text))
        },
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .fillMaxWidth()
    )
}