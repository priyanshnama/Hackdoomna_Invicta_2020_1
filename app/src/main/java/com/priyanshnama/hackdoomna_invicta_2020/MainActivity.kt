package com.priyanshnama.hackdoomna_invicta_2020

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    var txt_pathShow: TextView? = null
    var encrypt: Button? = null
    var decrypt: Button? = null
    var myFileIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_pathShow = findViewById<View>(R.id.txt_show) as TextView
        encrypt = findViewById<View>(R.id.encrypt) as Button
        decrypt = findViewById<View>(R.id.decrypt) as Button

        encrypt!!.setOnClickListener {
            myFileIntent = Intent(Intent.ACTION_GET_CONTENT)
            myFileIntent!!.type = "*/*"
            startActivityForResult(myFileIntent, 10)
        }

        decrypt!!.setOnClickListener {
            myFileIntent = Intent(Intent.ACTION_GET_CONTENT)
            myFileIntent!!.type = "*/*"
            startActivityForResult(myFileIntent, 10)
        }


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        when (requestCode) {
            10 -> if (requestCode == Activity.RESULT_OK) {
                val path = data!!.data!!.path
                txt_pathShow!!.text = path
            }
        }
    }


    fun encrypt(path: String, a: Int, b : Int):String{
        var bytearray : ByteArray

        var filename = path
        var file = File("encrypt_" + path)
        var file2 = File("decrypt_" + path)
        file.createNewFile()

        bytearray=File(filename).readBytes()

        var bytearray2 = bytearray

        var arr = IntArray(bytearray.size)
        arr[a-1] = 1;arr[b-1] = -1;
        for(i in 0..bytearray.size-1){
            if((arr[i] > 0) or (arr[i] < 0)){
                if(i + a < bytearray.size)arr[i + a] = arr[i + a] + 1
                if(i + b < bytearray.size)arr[i + b] = arr[i + b] - 1
            }
        }
        for(i in 0..bytearray2.size-1)
        {
            if(arr[i] > 0){
                for(o in 0..arr[i])bytearray2[i]=bytearray2[i].plus(b).toByte()
            }
            if(arr[i] < 0){
                for(o in arr[i]..0)bytearray2[i]=bytearray2[i].minus(a).toByte()
            }
        }
        println(bytearray2.size)
        file2.writeBytes(bytearray2)

        return ("encrypt_" + path)
    }
}
