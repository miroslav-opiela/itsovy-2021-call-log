package sk.itsovy.android.calllog

import android.os.Bundle
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import sk.itsovy.android.calllog.databinding.FragmentMasterBinding

class MasterFragment : Fragment(), OnNumberClickListener {

    private lateinit var binding: FragmentMasterBinding
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMasterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = listOf(
            Call("112", CallLog.Calls.MISSED_TYPE),
            Call("0801123456", CallLog.Calls.OUTGOING_TYPE),
            Call("0949335087", CallLog.Calls.INCOMING_TYPE)
        )

        val adapter = CallLogAdapter(this)
        adapter.submitList(list)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onNumberClick(call: Call) {
        // oslovim view model a zmenim hodnotu live dat
        model.select(call)
    }

}

interface OnNumberClickListener {
    fun onNumberClick(call: Call)
}