@file:JvmName("ActionChill")

package org.firstinspires.ftc.teamcode.CommandFrameWork

import com.acmerobotics.dashboard.canvas.Canvas
import com.acmerobotics.roadrunner.Action
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard

/**
 * Run [a] to completion in a blocking loop.
 */
fun runBlockingChill(a: Action) {
//    val dash = FtcDashboard.getInstance()
    val c = Canvas()
    a.preview(c)
    a.run(Dashboard.packet)

//        dash.sendTelemetryPacket(p)
}
