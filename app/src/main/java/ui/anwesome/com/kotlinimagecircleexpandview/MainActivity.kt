package ui.anwesome.com.kotlinimagecircleexpandview

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.imagecircularexpandview.ImageCircleExpandView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImageCircleExpandView.create(this,BitmapFactory.decodeResource(resources,R.drawable.nature_more))
    }
}
