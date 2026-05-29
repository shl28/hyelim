package com.example.app_expense_room.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_expense_room.util.formatAmount

@Composable
fun CategoryChart(
    categoryExpenses: Map<String, Long>,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("카테고리별 지출", style = MaterialTheme.typography.titleMedium)

            if (categoryExpenses.isEmpty()) {
                Text(
                    "이번 달 지출 내역이 없습니다.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                val sorted = categoryExpenses.toList().sortedByDescending { it.second }
                val maxAmount = sorted.first().second.coerceAtLeast(1)

                sorted.forEach { (category, amount) ->
                    CategoryBar(
                        category = category,
                        amount = amount,
                        ratio = amount.toFloat() / maxAmount,
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryBar(
    category: String,
    amount: Long,
    ratio: Float,
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(category, style = MaterialTheme.typography.bodyMedium)
            Text(
                formatAmount(amount),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(ratio)
                    .height(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}
