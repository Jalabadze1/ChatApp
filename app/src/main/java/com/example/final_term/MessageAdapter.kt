package com.example.final_term

import android.content.Context
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    val ITEM_RECIVE =1;
    val ITEM_SENT = 2;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){

            val view: View = LayoutInflater.from(context).inflate(R.layout.recive, parent, false)
            return ReciveViewHolder(view)

        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.send, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        }else{

            val viewHolder = holder as ReciveViewHolder
            holder.reciveMessage.text = currentMessage.message

        }

    }

    override fun getItemCount(): Int {

        return messageList.size




    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return ITEM_SENT
        }else {
            return ITEM_RECIVE
        }
    }


    class  SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.sent_message)

    }

    class ReciveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val reciveMessage = itemView.findViewById<TextView>(R.id.recive_message)

    }

}