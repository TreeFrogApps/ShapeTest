package com.treefrogapps.shapeview.shapeviewtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.shape_layout.*

class ShapeActivity : AppCompatActivity() {

    private var sides = 3

    companion object {
        const val SIDES_COUNT_KEY = "sides_count_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shape_layout)
        if (savedInstanceState != null) sides = savedInstanceState.getInt(SIDES_COUNT_KEY)
        sidesSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sides = progress; drawShape()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        drawShape()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SIDES_COUNT_KEY, sides)
    }

    private fun drawShape() {
        sidesTextView.text = sides.toString()
        shapeView.setSides(sides)
        shapeView.redraw()
    }
}