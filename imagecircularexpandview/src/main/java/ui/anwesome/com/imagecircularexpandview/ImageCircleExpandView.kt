package ui.anwesome.com.imagecircularexpandview

/**
 * Created by anweshmishra on 08/02/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class ImageCircleExpandView(ctx:Context,var bitmap:Bitmap):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        return true
    }
    data class Animator(var view:View,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class State(var prevScale:Float = 0f,var j:Int = 0, var dir:Float = 0f,var jDir:Int = 1) {
        val scales:Array<Float> = arrayOf(0f,0f)
        fun update(stopcb:(Float)->Unit) {
            scales[j] += 0.1f*dir
            if(Math.abs(scales[j] - prevScale) > 1) {
                scales[j] = prevScale + dir
                j += jDir
                if(j == scales.size || j == -1) {
                    jDir *= -1
                    j += jDir
                    prevScale = scales[jDir] + dir
                    dir = 0f
                    stopcb(prevScale)
                }
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0f) {
                dir = 1f - 2*prevScale
                startcb()
            }
        }
    }
    data class ImageCircleExpand(var bitmap:Bitmap,var w:Int,var h:Int,var r:Int = Math.min(w,h)/3) {
        val state = State()
        init {
            bitmap = Bitmap.createScaledBitmap(bitmap,2*r,2*r,true)
        }
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(w.toFloat()/2,h.toFloat()/2)
            canvas.drawCircle(0f,0f,r.toFloat(),paint)
            canvas.save()
            canvas.rotate(180f*(1-state.scales[1]))
            val path = Path()
            path.addCircle(0f,0f,r*state.scales[1],Path.Direction.CW)
            canvas.clipPath(path)
            canvas.drawBitmap(bitmap,-r.toFloat(),-r.toFloat(),paint)
            canvas.restore()
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
}