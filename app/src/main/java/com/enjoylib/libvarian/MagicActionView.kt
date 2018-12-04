package com.enjoylib.libvarian

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MagicActionView : View, View.OnLongClickListener {

    var locX = 0f
    var locY = 0f

    val paint = Paint()
    val childPaint = Paint()

    private var isLongClicked = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        paint.color = Color.RED
        childPaint.color = Color.BLUE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                isLongClicked = true
                postDelayed({
                    locX = x!!
                    locY = y!!
                    invalidate()
                }, 300)
            }
            MotionEvent.ACTION_UP -> {
                isLongClicked = false
                invalidate()
            }
        }
        return true
    }

    override fun onLongClick(v: View?): Boolean {

        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isLongClicked) {
            canvas?.drawCircle(locX, locY, 50f, paint)
            canvas?.drawCircle(locX - 150f, locY - 120f, 70f, childPaint)
            canvas?.drawCircle(locX - 0f, locY - 150f, 70f, childPaint)
            canvas?.drawCircle(locX + 150f, locY - 120f, 70f, childPaint)
        }

    }
}