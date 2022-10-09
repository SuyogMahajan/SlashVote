package com.slashvote.slashvote.ComposeScreens.models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.a_droid.slashvote.ui.theme.MudyWhite


@Composable
fun PercentageBar(
    text: String,
    isPercentageBar: Boolean = false,
    modifier: Modifier = Modifier,
    percentage: Float
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .border( width = 0.dp,
                color = Color.Transparent, shape = RoundedCornerShape(20.dp))
            .background(MudyWhite)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(percentage)
                .background(
                    if (isPercentageBar) Color.Gray else MudyWhite,
                    RoundedCornerShape(20.dp)
                )
                .padding(15.dp), contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "")
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                .align(Alignment.CenterStart)
        )

        if (isPercentageBar)
            Text(
                text = String.format("%.2f",percentage * 100) + "%",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(15.dp)
            )
    }
}
