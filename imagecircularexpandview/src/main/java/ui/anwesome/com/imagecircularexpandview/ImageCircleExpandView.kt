package ui.anwesome.com.imagecircularexpandview

/**
 * Created by anweshmishra on 08/02/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class ImageCircleExpandView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        return true
    }
}