package com.example.anew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateActivity : AppCompatActivity(), View.OnClickListener {
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        save.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
    }
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }
    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.save -> {
                val getUserID = auth!!.currentUser!!.uid
                val database = FirebaseDatabase.getInstance()

                val getHari: String = hari.getText().toString()
                val getTanggal: String = tanggal.getText().toString()
                val getSuhu: String = suhu.getText().toString()
                val getKeluhan: String = keluhan.getText().toString()
                val getKontak: String = kontak.getText().toString()

                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getHari) || isEmpty(getTanggal) || isEmpty(getSuhu) || isEmpty(
                        getKeluhan
                    ) || isEmpty(getKontak)
                ) {
                    Toast.makeText(
                        this@CreateActivity, "Data tidak boleh ada yang kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    getReference.child("Admin").child(getUserID).child("COVID").push()
                        .setValue(
                            data_pengguna(
                                getHari,
                                getTanggal,
                                getSuhu,
                                getKeluhan,
                                getKontak
                            )
                        )
                        .addOnCompleteListener(this) {
                            hari.setText("")
                            tanggal.setText("")
                            suhu.setText("")
                            keluhan.setText("")
                            kontak.setText("")
                            Toast.makeText(
                                this@CreateActivity, "Data Tersimpan",
                                Toast.LENGTH_SHORT
                            ).show()
                            intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                }

            }
        }
    }
}