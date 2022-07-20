package com.example.donutApp.extension

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TypeWriter : AppCompatTextView {

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)

    private val mHandler: Handler = Handler()

    private var typeWriterText: CharSequence? = null
    private var typeWriterIndex = 0
    private var typeWriterDelay: Long = 150L

    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = typeWriterText?.let {
                it.subSequence(0, typeWriterIndex++)
            }
            if (typeWriterIndex <= (typeWriterText?.length ?: 0)) {
                mHandler.postDelayed(this, typeWriterDelay)
            }
        }
    }

    fun animateText(writerText: CharSequence?) {
        typeWriterText = writerText
        typeWriterIndex = 0
        text = ""
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, typeWriterDelay)
    }

    fun setCharacterDelay(delay: Long) {
        typeWriterDelay = delay
    }
}
