package com.example.composeguitests.data.model

data class SensorRawValues(
    //2 byte per pressure value
    val pressure1_mV: Int,
    val pressure2_mV: Int,
    val pressure3_mV: Int,
    val pressure4_mV: Int,
    val pressureTank_mV: Int,
    //---- 10 bytes
    //1 byte per position value
    val pos1_percents: Int,
    val pos2_percents: Int,
    val pos3_percents: Int,
    val pos4_percents: Int,
    //---- 14 bytes
)
