package com.fap.diplomble.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.fap.diplomble.R
import com.fap.diplomble.dpToPx

class RoomView constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    companion object {
        private const val DEFAULT_STROKE_COLOR_ID = R.color.design_default_color_primary
        private const val DEFAULT_BEACON_COLOR_ID = R.color.design_default_color_on_secondary
        private const val DEFAULT_USER_MARK_COLOR_ID = R.color.teal_700

        private const val DEFAULT_STROKE_WIDTH = 10f
        private const val DEFAULT_USER_MARK_WIDTH = 10f
        private const val DEFAULT_USER_MARK_HEIGHT = 10f
        private const val DEFAULT_BEACON_WIDTH = 10f
        private const val DEFAULT_BEACON_HEIGHT = 10f

        private const val MIN_VIEW_WIDTH = 30f
        private const val MIN_VIEW_HEIGHT = 30f
    }

    init {
        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.RoomView,
        ).apply {

            //COLORS
            colorOfStroke = getColor(
                R.styleable.RoomView_strokeColor,
                ContextCompat.getColor(context, DEFAULT_STROKE_COLOR_ID)
            )

            colorOfBeacon = getColor(
                R.styleable.RoomView_beaconColor,
                ContextCompat.getColor(context, DEFAULT_BEACON_COLOR_ID)
            )

            colorOfUser = getColor(
                R.styleable.RoomView_userMarkColor,
                ContextCompat.getColor(context, DEFAULT_USER_MARK_COLOR_ID)
            )

            //SIZES
            strokeWidth = getDimension(
                R.styleable.RoomView_strokeWidth,
                context.dpToPx(DEFAULT_STROKE_WIDTH)
            )

            beaconIndicatorWidth = getDimension(
                R.styleable.RoomView_beaconWidth,
                context.dpToPx(DEFAULT_BEACON_WIDTH)
            )
            beaconIndicatorHeight = getDimension(
                R.styleable.RoomView_beaconHeight,
                context.dpToPx(DEFAULT_BEACON_HEIGHT)
            )

            userMarkWidth = getDimension(
                R.styleable.RoomView_userMarkWidth,
                context.dpToPx(DEFAULT_USER_MARK_WIDTH)
            )
            userMarkHeight = getDimension(
                R.styleable.RoomView_userMarkHeight,
                context.dpToPx(DEFAULT_USER_MARK_HEIGHT)
            )

            recycle()
        }
    }

    var colorOfStroke: Int = context.getColor(R.color.design_default_color_primary)
    var colorOfBeacon: Int = context.getColor(R.color.design_default_color_on_secondary)
    var colorOfUser: Int = Color.BLUE

    var beaconIndicatorWidth: Float
    var beaconIndicatorHeight: Float

    var userMarkWidth: Float
    var userMarkHeight: Float

    var strokeWidth: Float

    private val roomPaint = Paint().apply {
        color = colorOfStroke
        style = Paint.Style.STROKE
        strokeWidth = this@RoomView.strokeWidth
    }

    private val beaconPaint = Paint().apply {
        color = colorOfBeacon
        style = Paint.Style.FILL
    }

    private val userPaint = Paint().apply {
        color = colorOfUser
        style = Paint.Style.FILL
    }

    private var userMarkRectF: RectF? = null

    private val rectF = RectF(
        0f,
        0f,
        width.toFloat(),
        height.toFloat()
    )

    private val beaconsRectList: MutableList<RectF> = mutableListOf()

    private val beaconMutableList: MutableList<BeaconCoordinates> = mutableListOf()
    val beaconList: List<BeaconCoordinates>
        get() = beaconMutableList


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(context.dpToPx(MIN_VIEW_WIDTH).toInt(), widthMeasureSpec)
        val height = resolveSize(context.dpToPx(MIN_VIEW_HEIGHT).toInt(), heightMeasureSpec)
        rectF.right = width.toFloat()
        rectF.bottom = height.toFloat()
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            rectF,
            roomPaint
        )
        beaconsRectList.forEach {
            canvas.drawRect(
                it,
                beaconPaint
            )
        }
        userMarkRectF?.let {
            canvas.drawRect(
                it,
                userPaint
            )
        }
    }

    fun setUserMark(
        x: Float,
        y: Float
    ) {
        userMarkRectF = RectF()
        userMarkRectF!!.left = x
        userMarkRectF!!.top = y
        userMarkRectF!!.right = userMarkRectF!!.left + beaconIndicatorWidth
        userMarkRectF!!.bottom = userMarkRectF!!.top + beaconIndicatorHeight
        invalidate()
    }

    fun setBeacons(beaconCoordinates: List<BeaconCoordinates>) {
        clearBeacons()
        addBeacons(beaconCoordinates)
    }

    fun addBeacons(beaconCoordinates: List<BeaconCoordinates>) {
        beaconCoordinates.forEach { addBeacon(it) }
        invalidate()
    }

    private fun addBeacon(beaconCoordinates: BeaconCoordinates) {
        val leftRectBeacon = beaconCoordinates.getX(width)
        val topRectBeacon = beaconCoordinates.getY(height)
        beaconMutableList.add(beaconCoordinates)
        beaconsRectList.add(
            RectF(
                leftRectBeacon,
                topRectBeacon,
                leftRectBeacon + beaconIndicatorWidth,
                topRectBeacon + beaconIndicatorHeight
            )
        )
    }

    private fun clearBeacons() = beaconsRectList.clear()
}

data class BeaconCoordinates(
    val xOffset: Float,
    val yOffset: Float,
    val minor: Int,
) {
    fun getX(parentWidth: Int): Float = parentWidth * xOffset

    fun getY(parentHeight: Int): Float = parentHeight * yOffset
}