package com.treefrogapps.shapeview.shapeviewtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.*

class ShapeSurfaceView : SurfaceView, SurfaceHolder.Callback {

    private var sides: Int = 4
    private val paint = Paint()
    private var path = Path()

    constructor(context: Context) : super(context) {
        setParams()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setParams()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setParams()
    }

    fun setSides(sides: Int) {
        this.sides = sides
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        val canvas = holder?.lockCanvas(null)
        drawShape(width.toDouble(), height.toDouble())
        canvas?.drawPath(path, paint)
        holder?.unlockCanvasAndPost(canvas)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        holder?.removeCallback(this@ShapeSurfaceView)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        holder?.addCallback(this@ShapeSurfaceView)
    }

    private fun setParams() {
        holder?.addCallback(this@ShapeSurfaceView)
        setWillNotDraw(false)

        paint.strokeWidth = 2.0F
        paint.color = Integer.decode("0x0704d1")
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = false
    }

    private fun drawShape(w: Double, h: Double) {
        val center = (w / 2.0) + (h / 2.0)
        val radius = w / 2.0
        val segment = 360.0 / sides.toDouble()
        drawPoints(center.toFloat(), radius, segment, w.toFloat(), h.toFloat())
    }

    private fun quadrant(angle: Double): Int {
        return when (angle) {
            in 0.0..90.0    -> 1
            in 90.0..180.0  -> 2
            in 180.0..270.0 -> 3
            in 270.0..360.0 -> 4
            else            -> -1
        }
    }

    private fun drawPoints(center: Float, radius: Double, segment: Double, width: Float, height: Float) {
        path.moveTo(width, height / 2.0F) //start position

        for (i in 1 until sides) {
            val angle = segment * i.toDouble()
            val x = (Math.sin(angle) * radius).toFloat()
            val y = (Math.cos(angle) * radius).toFloat()
            path.lineTo(center + x, center + y)
        }

        path.lineTo(width, height / 2.0F) // join up to start
    }
}
