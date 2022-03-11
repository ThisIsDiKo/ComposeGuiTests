package com.example.composeguitests.domain

import com.example.composeguitests.data.model.ControllerConfig
import com.example.composeguitests.data.model.SensorRawValues

interface RemoteControllerRepository {
    suspend fun connect() //maybe need to be suspend

    suspend fun writeOutput(msg: String)

    suspend fun requestSensorData(): SensorRawValues

    suspend fun requestConfig(): ControllerConfig
    suspend fun writeConfig(config: ControllerConfig)

    suspend fun enableAlarmNotification()
    suspend fun disableAlarmNotification()
}