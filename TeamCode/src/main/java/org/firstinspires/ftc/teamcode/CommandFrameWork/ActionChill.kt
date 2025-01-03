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
    val p = Dashboard.packet
    p.fieldOverlay().operations.addAll(c.operations)
    a.run(p)

//        dash.sendTelemetryPacket(p)
}
