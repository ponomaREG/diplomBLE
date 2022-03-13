package com.fap.diplomble.presentation.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.fap.diplomble.R
import com.fap.diplomble.dpToPx
import com.fap.diplomble.util.getXByOffset
import com.fap.diplomble.util.getYByOffset

class RoomView constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    companion object {
        private const val DEFAULT_STROKE_COLOR_ID = R.color.design_default_color_primary
        private const val DEFAULT_BEACON_COLOR_ID = R.color.design_default_color_on_secondary
        private const val DEFAULT_USER_MARK_COLOR_ID = R.color.teal_700

        private const val DEFAULT_FINGERPRINT_COLOR = Color.BLUE

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
            roomStrokeWidth = getDimension(
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
    var colorOfUser: Int = Color.GREEN

    var beaconIndicatorWidth: Float
    var beaconIndicatorHeight: Float

    var userMarkWidth: Float
    var userMarkHeight: Float

    var roomStrokeWidth: Float

    private val roomPaint = Paint().apply {
        color = colorOfStroke
        style = Paint.Style.STROKE
        strokeWidth = this@RoomView.roomStrokeWidth
    }

    private val beaconPaint = Paint().apply {
        color = colorOfBeacon
        style = Paint.Style.FILL
    }

    private val fingerprintPaint = Paint().apply {
        color = DEFAULT_FINGERPRINT_COLOR
        style = Paint.Style.FILL
    }

    private val userPaint = Paint().apply {
        color = colorOfUser
        style = Paint.Style.FILL
    }

    private var userMarkFingerprint: Fingerprint? = null

    private val rectRoom = RectF(
        0f,
        0f,
        width.toFloat(),
        height.toFloat()
    )

    private val beaconsRectList: MutableList<RectF> = mutableListOf()

    private val fingerprintsRect: MutableList<FingerprintView> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(context.dpToPx(MIN_VIEW_WIDTH).toInt(), widthMeasureSpec)
        val height = resolveSize(context.dpToPx(MIN_VIEW_HEIGHT).toInt(), heightMeasureSpec)
        rectRoom.right = width.toFloat()
        rectRoom.bottom = height.toFloat()
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            rectRoom,
            roomPaint
        )
        beaconsRectList.forEach {
            canvas.drawRect(
                it,
                beaconPaint
            )
        }
        fingerprintsRect.forEach {
            canvas.drawCircle(
                it.rectF.left,
                it.rectF.top,
                userMarkWidth,
                if (it.fingerprint.id == userMarkFingerprint?.id) userPaint else fingerprintPaint
            )
        }
    }

    fun setUserMark(
        fingerprintId: Int
    ) {
        userMarkFingerprint = fingerprintsRect.find {
            it.fingerprint.id == fingerprintId
        }?.fingerprint
        invalidate()
    }

    fun setFingerprints(fingerprints: List<Fingerprint>) {
        fingerprints.forEach {
            fingerprintsRect.add(
                FingerprintView(
                    it,
                    RectF(
                        getXByOffset(it.xOffset, width.toFloat()),
                        getYByOffset(it.yOffset, height.toFloat()),
                        getXByOffset(it.xOffset, width.toFloat()) + userMarkWidth,
                        getYByOffset(it.yOffset, height.toFloat()) + userMarkHeight
                    )
                )
            )
        }
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
        val leftRectBeacon = getXByOffset(beaconCoordinates.xOffset, width.toFloat())
        val topRectBeacon = getYByOffset(beaconCoordinates.yOffset, height.toFloat())
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

private data class FingerprintView(
    val fingerprint: Fingerprint,
    val rectF: RectF
)

data class BeaconCoordinates(
    val xOffset: Float,
    val yOffset: Float,
    val minor: Int,
)
