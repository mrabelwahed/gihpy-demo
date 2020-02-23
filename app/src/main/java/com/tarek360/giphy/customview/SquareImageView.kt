package com.tarek360.giphy.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.tarek360.giphy.R

class SquareImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private val fitType: FitType

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView)
        val fit = a.getInt(R.styleable.SquareImageView_fit, 0)
        fitType = if (fit == 0) {
            FitType.FIT_WIDTH
        } else {
            FitType.FIT_HEIGHT
        }
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = when (fitType) {
            FitType.FIT_WIDTH -> measuredWidth
            FitType.FIT_HEIGHT -> measuredHeight
        }
        setMeasuredDimension(size, size)
    }

    enum class FitType {
        FIT_WIDTH,
        FIT_HEIGHT,
    }
}
