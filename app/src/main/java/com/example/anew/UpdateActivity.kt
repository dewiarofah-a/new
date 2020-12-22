package com.example.anew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekHari: String? = null
    private var cekTanggal: String? = null
    private var cekSuhu: String? = null
    private var cekKeluhan: String? = null
    private var cekKontak: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        data
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                cekHari = new_hari.getText().toString()
                cekTanggal = new_tanggal.getText().toString()
                cekSuhu = new_suhu.getText().toString()
                cekKeluhan = new_keluhan.getText().toString()
                cekKontak = new_kontak.getText().toString()

                if (isEmpty(cekHari!!) || isEmpty(cekTanggal!!) || isEmpty(cekSuhu!!)|| isEmpty(cekKeluhan!!) || isEmpty(cekKontak!!)) {
                    Toast.makeText(
                        this@UpdateActivity,
                        "Data tidak boleh ada yang kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val setPengguna = data_pengguna()
                    setPengguna.hari = new_hari.getText().toString()
                    setPengguna.tanggal = new_tanggal.getText().toString()
                    setPengguna.suhu = new_suhu.getText().toString()
                    setPengguna.keluhan = new_keluhan.getText().toString()
                    setPengguna.kontak = new_kontak.getText().toString()
                    updatePengguna(setPengguna)
                }
            }
        })
    }
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }
    private val data: Unit
        private get() {
            val getHari = intent.extras!!.getString("dataHari")
            val getTanggal = intent.extras!!.getString("dataTanggal")
            val getSuhu = intent.extras!!.getString("dataSuhu")
            val getKeluhan = intent.extras!!.getString("dataKeluhan")
            val getKontak = intent.extras!!.getString("dataKontak")
            new_hari!!.setText(getHari)
            new_tanggal!!.setText(getTanggal)
            new_keluhan!!.setText(getSuhu)
            new_keluhan!!.setText(getKeluhan)
            new_kontak!!.setText(getKontak)
        }

    private fun updatePengguna(pengguna: data_pengguna) {
        val userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("COVID")
            .child(getKey!!)
            .setValue(pengguna)
            .addOnSuccessListener {
                new_hari!!.setText("")
                new_tanggal!!.setText("")
                new_suhu!!.setText("")
                new_keluhan!!.setText("")
                new_kontak!!.setText("")
                Toast.makeText(this@UpdateActivity, "Data Berhasil diubah",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}