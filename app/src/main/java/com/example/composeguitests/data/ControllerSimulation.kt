package com.example.composeguitests.data

import android.util.Log
import com.example.composeguitests.data.model.ControllerConfig
import com.example.composeguitests.data.model.SensorRawValues
import com.example.composeguitests.domain.RemoteControllerRepository
import kotlinx.coroutines.delay
import kotlin.random.Random

class ControllerSimulation: RemoteControllerRepository {

    var isConnected: Boolean = false
    var alarmNotificationEnabled: Boolean = false

    override suspend fun connect() {
        delay(3000)
        Log.d("Simulator TAG", "Controller connected")
        isConnected = true
    }

    override suspend fun writeOutput(msg: String) {
        Log.d("Simulator TAG", "Sending message: $msg")
    }

    override suspend fun requestSensorData(): SensorRawValues {
        delay(5)
        return SensorRawValues(
            pressure1_mV = Random.nextInt(500, 4500),
            pressure2_mV = Random.nextInt(500, 4500),
            pressure3_mV = Random.nextInt(500, 4500),
            pressure4_mV = Random.nextInt(500, 4500),
            pressureTank_mV = Random.nextInt(500, 4500),

            pos1_percents = Random.nextInt(0, 100),
            pos2_percents = Random.nextInt(0, 100),
            pos3_percents = Random.nextInt(0, 100),
            pos4_percents = Random.nextInt(0, 100),
        )
    }

    override suspend fun requestConfig(): ControllerConfig {
        TODO("Not yet implemented")
    }

    override suspend fun writeConfig(config: ControllerConfig) {
        TODO("Not yet implemented")
    }

    override suspend fun enableAlarmNotification() {
        if (alarmNotificationEnabled){
            Log.d("Simulator TAG", "Alarm Notifications is already Enabled")
            return
        }
        delay(1000)
        Log.d("Simulator TAG", "Alarm Notifications Enabled")
        alarmNotificationEnabled = true
    }

    override suspend fun disableAlarmNotification() {
        delay(1000)
        Log.d("Simulator TAG", "Alarm Notifications Disabled")
        alarmNotificationEnabled = false
    }

}
