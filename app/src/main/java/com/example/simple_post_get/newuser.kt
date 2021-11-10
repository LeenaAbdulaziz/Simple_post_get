package com.example.simple_post_get

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class newuser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newuser)

        val name = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val savebtn = findViewById<View>(R.id.button) as Button

        savebtn.setOnClickListener {

            var f = People(name.text.toString())

            addSingleuser(f, onResult = {
                name.setText("")

                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }
    }

    private fun addSingleuser(f: People, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIinterface::class.java)
        val progressDialog = ProgressDialog(this@newuser)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addUser(f).enqueue(object : Callback<People> {
                override fun onResponse(call: Call<People>, response: Response<People>) {

                    onResult()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<People>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        }
    }

    fun addnew(view: android.view.View) {
        intent = Intent(applicationContext, newuser::class.java)
        startActivity(intent)
    }

    fun back(view: View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}