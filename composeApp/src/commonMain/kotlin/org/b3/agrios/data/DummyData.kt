package org.b3.agrios.data

import org.b3.agrios.model.AlertItem
import org.b3.agrios.model.RecommendationLevel
import org.b3.agrios.model.Severity
import org.b3.agrios.model.MoistureStatus
import org.b3.agrios.model.WeatherDay
import org.b3.agrios.model.Zone

object DummyData {
    val zones = listOf(
        Zone(
            "A-01",
            MoistureStatus.OPTIMAL,
            1250,
            RecommendationLevel.NONE
        ),
        Zone(
            "A-02",
            MoistureStatus.OPTIMAL,
            1430,
            RecommendationLevel.NONE
        ),
        Zone(
            "B-01",
            MoistureStatus.LOW,
            2150,
            RecommendationLevel.HIGH
        ),
        Zone(
            "B-02",
            MoistureStatus.DRY,
            2580,
            RecommendationLevel.HIGH
        ),
        Zone(
            "C-01",
            MoistureStatus.OPTIMAL,
            1180,
            RecommendationLevel.NONE
        ),
        Zone(
            "C-02",
            MoistureStatus.VERY_DRY,
            2950,
            RecommendationLevel.HIGH
        ),
    )

    val alerts = listOf(
        AlertItem(
            "C-02 ゾーン：非常に乾燥",
            "Δ値が 2,900 を超えました",
            "14:20",
            Severity.CRITICAL
        ),
        AlertItem(
            "B-02 ゾーン：灌水推奨レベル",
            "Δ値が 2,200 を超えました",
            "14:15",
            Severity.WARNING
        ),
        AlertItem(
            "タンク水位：低下",
            "貯水タンクの水位が 30% を下回っています",
            "13:50",
            Severity.INFO
        ),
    )

    val weather = listOf(
        WeatherDay("今日 5/24", "☀", 31, 18, 10),
        WeatherDay("明日 5/25", "☀", 32, 19, 10),
        WeatherDay("月 5/26", "⛅", 28, 18, 30),
        WeatherDay("火 5/27", "🌧", 24, 17, 70),
        WeatherDay("水 5/28", "☁", 25, 16, 40),
    )

    val moistureHistory = listOf(
        1500, 1420, 1480, 1550, 1710, 1780, 1760, 1840,
        1930, 2100, 2120, 2280, 2420, 2380, 2360, 2480,
        2620, 2680, 2600, 2520, 2430, 2300, 2200, 2150
    )
}
