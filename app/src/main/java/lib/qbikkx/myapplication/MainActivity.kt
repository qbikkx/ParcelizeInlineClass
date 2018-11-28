package lib.qbikkx.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*


inline class TheSubject(val value: String)

@Parcelize
data class TestObject(val subject: TheSubject) : Parcelable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer.id,
                MainFragment.newInstance(TestObject(subject = TheSubject("hello hell in fragment"))))
            .commit()
    }
}

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = arguments!!.getParcelable<TestObject>("test") //here we are fine.

        startActivity(Intent(activity, SecondActivity::class.java).apply {
            putExtra("test", TestObject(subject = TheSubject("hello hell")))
        })
    }

    companion object {

        fun newInstance(test: TestObject) = MainFragment().apply {
            arguments = Bundle().apply {
                putParcelable("test", test)
            }
        }
    }
}

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val result = intent.getParcelableExtra<TestObject>("test") //crash occurs
    }
}