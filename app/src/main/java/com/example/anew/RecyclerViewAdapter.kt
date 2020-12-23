package com.example.anew

import android.annotation.SuppressLint
import android.app.AlertDialog
<<<<<<< HEAD
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
=======
import android.app.LauncherActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.sip.SipSession
>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD

class RecyclerViewAdapter (private  val listPengguna: ArrayList<data_pengguna>,context:Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
        private var context: Context
=======
import org.w3c.dom.Text

class RecyclerViewAdapter (private  val listPengguna: ArrayList<data_pengguna>,context:Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
        private val context: Context
>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3

        inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            val Hari: TextView
            val Tanggal: TextView
            val Suhu:TextView
            val Keluhan: TextView
            val Kontak:TextView
            val ListItem:LinearLayout

            init {
                Hari=itemView.findViewById(R.id.harix)
                Tanggal=itemView.findViewById(R.id.tanggalx)
                Suhu=itemView.findViewById(R.id.suhux)
                Keluhan=itemView.findViewById(R.id.keluhanx)
                Kontak=itemView.findViewById(R.id.kontakx)
                ListItem=itemView.findViewById(R.id.list_item)
            }

        }

<<<<<<< HEAD

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val V:View = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent,false)
        return ViewHolder(V)
    }


=======
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val V:View=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design,parent,false)
        return ViewHolder(V)
    }

>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Hari:String?=listPengguna.get(position).hari
        val Tanggal:String?=listPengguna.get(position).tanggal
        val Suhu:String?=listPengguna.get(position).suhu
        val Keluhan:String?=listPengguna.get(position).keluhan
        val Kontak:String?=listPengguna.get(position).kontak

<<<<<<< HEAD
        holder.Hari.text="hari: $Hari"
        holder.Tanggal.text="tanggal: $Tanggal"
        holder.Suhu.text="suhu: $Suhu"
        holder.Keluhan.text="keluhan: $Keluhan"
        holder.Kontak.text="kontak: $Kontak"

=======
        holder.Hari.text="Hari: $Hari"
        holder.Tanggal.text="Tanggal: $Tanggal"
        holder.Suhu.text="Suhu: $Suhu"
        holder.Keluhan.text="Keluhan: $Keluhan"
        holder.Kontak.text="Kontak: $Kontak"
>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3
        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                holder.ListItem.setOnLongClickListener { view ->
                    val action = arrayOf("Update", "Delete")
                    val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                        when (i) {
                            0 -> {
                                val bundle = Bundle()
                                bundle.putString("hari", listPengguna[position].hari)
                                bundle.putString("tanggal", listPengguna[position].tanggal)
                                bundle.putString("suhu", listPengguna[position].suhu)
                                bundle.putString("keluhan", listPengguna[position].keluhan)
                                bundle.putString("kontak", listPengguna[position].kontak)
                                bundle.putString("getPrimaryKey", listPengguna[position].key)
                                val intent = Intent(view.context, UpdateActivity::class.java)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }
                            1 -> {
<<<<<<< HEAD

=======
>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3
                            }
                        }
                    })
                    alert.create()
                    alert.show()
                    true
                }
                return true;
            }
        })
    }

    override fun getItemCount(): Int {
        return listPengguna.size
    }

    init {
<<<<<<< HEAD
        this.context = context
    }

=======
        this.context=context
    }


>>>>>>> 0fc3dd02a03c3fc8549e540923f860411c9117b3
}
