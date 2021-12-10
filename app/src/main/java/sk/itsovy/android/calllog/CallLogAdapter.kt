package sk.itsovy.android.calllog

import android.graphics.Color
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CallLogAdapter(private val listener: OnNumberClickListener) : ListAdapter<Call, CallLogAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(itemView: View, private val listener: OnNumberClickListener) : RecyclerView.ViewHolder(itemView) {

        private val textView : TextView = itemView.findViewById(android.R.id.text1)

        fun bind(item: Call) {
            textView.text = item.number
            if (item.type == CallLog.Calls.MISSED_TYPE) {
                textView.setBackgroundColor(Color.RED)
            }
            if (item.type == CallLog.Calls.OUTGOING_TYPE) {
                textView.setBackgroundColor(Color.BLUE)
            }
            if (item.type == CallLog.Calls.INCOMING_TYPE) {
                textView.setBackgroundColor(Color.GREEN)
            }
            textView.setOnClickListener {
                listener.onNumberClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layout = LayoutInflater.from(parent.context)
           .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<Call>() {
        override fun areItemsTheSame(oldItem: Call, newItem: Call): Boolean =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: Call, newItem: Call): Boolean =
            oldItem == newItem

    }

}
