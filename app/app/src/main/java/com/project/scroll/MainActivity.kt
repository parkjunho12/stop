package com.project.scroll

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color.parseColor
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var itemList = arrayListOf<Item>(
        Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
    ,  Item("dfkj","sdfsdf")
    ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")
        ,  Item("dfkj","sdfsdf")  ,  Item("dfkj","sdfsdf")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = window.decorView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (view != null) {

                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.parseColor("#ffffff")


            }

            val mAdapter = MainRvAdapter(this, itemList)
            recyclerview.adapter = mAdapter
            val myLayoutManager = GridLayoutManager(this, 2)
            recyclerview.layoutManager = myLayoutManager

        }

    }
}
