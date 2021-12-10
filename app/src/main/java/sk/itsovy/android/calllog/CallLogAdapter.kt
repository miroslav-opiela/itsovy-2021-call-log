package sk.itsovy.android.calllog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CallLogAdapter : ListAdapter<Call, CallLogAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView : TextView = itemView.findViewById(android.R.id.text1)

        fun bind(item: Call) {
            textView.text = item.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layout = LayoutInflater.from(parent.context)
           .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(layout)
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
