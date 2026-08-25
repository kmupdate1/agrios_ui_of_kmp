package org.b3.agrios.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.b3.agrios.model.MoistureStatus
import org.b3.agrios.model.Zone

@Composable
fun IrrigationRecommendation(
    zone: Zone,
    modifier: Modifier = Modifier,
) {
    Card(modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("AI灌水推奨", fontWeight = FontWeight.Bold)
            Text("${zone.id} ゾーン", fontWeight = FontWeight.Bold)
            Text(
                when (zone.status) {
                    MoistureStatus.VERY_DRY -> "非常に乾燥しています。24時間以内の灌水を推奨します。"
                    MoistureStatus.DRY -> "土壌水分が低下しています。24時間以内に灌水を推奨します。"
                    MoistureStatus.LOW -> "土壌水分が低下傾向です。状況を確認してください。"
                    MoistureStatus.OPTIMAL -> "現在の土壌水分は適正範囲です。"
                }
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {}, Modifier.weight(1f)) { Text("灌水を実行") }
                OutlinedButton(onClick = {}, Modifier.weight(1f)) { Text("延期する") }
            }
        }
    }
}
