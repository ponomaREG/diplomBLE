package com.fap.diplomble.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children

class RoomViewGroup constructor(
    context: Context,
    attributeSet: AttributeSet
): LinearLayout(
    context,
    attributeSet
) {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    private val rooms: MutableList<RoomView> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        children.forEach { child ->
            addView(child)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun addRoom(roomView: RoomView) {
        rooms.add(roomView)
        addView(roomView)
        requestLayout()
    }

    fun setRooms(roomList: List<RoomView>) {
        rooms.clear()
        rooms.addAll(roomList)
        updateChildren()
        requestLayout()
    }

    fun clearRooms() {
        rooms.clear()
        requestLayout()
    }

    private fun updateChildren() {
        removeAllViews()
        rooms.forEach { addView(it) }
    }

    private fun putView(child: View, left: Int, topOfChildren: Int) {
        child.left = left
        child.top = topOfChildren
        child.right = left + child.measuredWidth
        child.bottom = child.top + child.measuredHeight
    }
}