package org.firstinspires.ftc.teamcode.CommandFrameWork

import com.acmerobotics.dashboard.canvas.Canvas
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action

class BetterParallelAction(var initialActions: List<Action>) : Action {

    override fun run(p: TelemetryPacket): Boolean {
        initialActions = initialActions.filter { it.run(p) }
        return initialActions.isNotEmpty()
    }

    override fun preview(fieldOverlay: Canvas) {
        for (a in initialActions) {
            a.preview(fieldOverlay)
        }
    }
}