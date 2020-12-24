package com.example.anew

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyListDataActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView?=null
    private var adapter:RecyclerView.Adapter<*>?=null
    private var layoutManager:RecyclerView.LayoutManager?=null

    val database = FirebaseDatabase.getInstance()
    private var dataPengguna=ArrayList<data_pengguna>()
    private var auth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list_data)
        recyclerView = findViewById(R.id.listdata)
        auth=FirebaseAuth.getInstance()
        MyRecycleView()
        GetData()
    }

    private fun GetData() {
        Toast.makeText(applicationContext,"mohon tunggu sebentar.", Toast.LENGTH_LONG).show()
        val getUserID:String=auth?.getCurrentUser()?.getUid().toString()
        val getReference=database.getReference()
        getReference.child("Admin").child(getUserID).child("COVID").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(datasnapshot: DataSnapshot) {
                        if(datasnapshot.exists()){
                            for (snapshot in datasnapshot.children){
                                val pengguna =snapshot.getValue(data_pengguna::class.java)
                                pengguna?.key=snapshot.key
                                dataPengguna.add(pengguna!!)
                            }

                            adapter=RecyclerViewAdapter(dataPengguna,this@MyListDataActivity)
                            recyclerView?.adapter=adapter
                            (adapter as RecyclerViewAdapter).notifyDataSetChanged()
                            Toast.makeText(applicationContext,"data berhasil dimuat", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext,"data gagal dimuat", Toast.LENGTH_LONG).show()
                        Log.e("MyListDataActivity",databaseError.details+" "+ databaseError.message)

                    }
                })
    }

    private  fun MyRecycleView(){
        layoutManager= LinearLayoutManager(this)
        recyclerView?.layoutManager= layoutManager
        recyclerView?.setHasFixedSize(true)

        val itemDecoration= DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.line)!!)
        recyclerView?.addItemDecoration(itemDecoration)
    }

}
