package com.treefrogapps.shapeview.shapeviewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.shape_layout.*

class ShapeActivity : AppCompatActivity() {

    private var sides = 3
    private var hue = 0.0F

    companion object {
        const val SIDES_COUNT_KEY = "sides_count_key"
        const val HUE_KEY = "hue_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shape_layout)
        if (savedInstanceState != null) {
            sides = savedInstanceState.getInt(SIDES_COUNT_KEY)
            hue = savedInstanceState.getFloat(HUE_KEY)
        }

        setupSeekBars()
    }

    override fun onResume() {
        super.onResume()
        drawShape()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.apply { putInt(SIDES_COUNT_KEY, sides); putFloat(HUE_KEY, hue) }
    }

    @SuppressLint("SetTextI18n")
    private fun drawShape() {
        sidesTextView.text = "Sides : $sides"
        shapeView.setSides(sides)
        shapeView.setColor(hue)
        shapeView.redraw()
    }

    private fun setupSeekBars() {
        sidesSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sides = progress; drawShape()
            }
        })

        colorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hue = progress.toFloat(); drawShape()
            }
        })
    }
}