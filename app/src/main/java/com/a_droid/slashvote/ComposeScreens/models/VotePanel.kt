package com.slashvote.slashvote.ComposeScreens.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a_droid.slashvote.ui.theme.LightBlue
import com.a_droid.slashvote.ui.theme.fontFamily
import com.slashvote.slashvote.data.Option

@Composable
fun VotePanel(
    modifier: Modifier = Modifier,
    isPercentageBar: Boolean = false,
    question: String,
    options: List<Option>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(LightBlue, RoundedCornerShape(20.dp))
            .then(modifier)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Who is your favorite actor ?",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center
            )

            LazyColumn {
                items(options.size) {
PercentageBar(text = options[it].text,isPercentageBar = isPercentageBar, percentage = options[it].percentage)
                }
            }
        }
    }
}