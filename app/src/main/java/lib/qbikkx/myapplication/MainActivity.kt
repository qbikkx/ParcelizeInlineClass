package lib.qbikkx.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, SecondActivity::class.java).apply {
            putExtra("test", TestObject(subject = TheSubject("hello hell")))
        })
    }
}

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val result = intent.getParcelableExtra<TestObject>("test")
    }
}

inline class TheSubject(val value: String)

@Parcelize
data class TestObject(val subject: TheSubject) : Parcelable