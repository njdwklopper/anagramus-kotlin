package tech.klopper.anagramus._base.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import java.util.*

class ProgressView : View {

    private var radiusA: Int = 0
    private var radiusB: Int = 0
    private var radiusMax: Int = 0

    private var dirA: Boolean = false
    private var dirB: Boolean = false

    private val r = {
        if (isShown) {
            invalidate()
        }
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        paint.isAntiAlias = true
        val rnd = Random()
        paint.style = Paint.Style.FILL
        paint.setARGB(128, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        dirA = false
        dirB = true
    }

    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radiusA = 0
        radiusB = w / 2
        radiusMax = w / 2
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            invalidate()
        } else {
            h.removeCallbacks(r)
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (radiusA >= radiusMax) {
            dirA = false
        } else if (radiusA <= 0) {
            dirA = true
        }
        if (radiusB >= radiusMax) {
            dirB = false
        } else if (radiusB <= 0) {
            dirB = true
        }

        if (dirA) {
            radiusA += STEPS
        } else {
            radiusA -= STEPS
        }
        if (dirB) {
            radiusB += STEPS
        } else {
            radiusB -= STEPS
        }

        canvas.drawCircle(radiusMax.toFloat(), radiusMax.toFloat(), radiusA.toFloat(),
            paint
        )
        canvas.drawCircle(radiusMax.toFloat(), radiusMax.toFloat(), radiusB.toFloat(),
            paint
        )
        h.postDelayed(r, 70)
    }

    companion object {

        private val paint = Paint()
        private val h = Handler()
        private val STEPS = 10// Higher value, faster animation
    }
}
