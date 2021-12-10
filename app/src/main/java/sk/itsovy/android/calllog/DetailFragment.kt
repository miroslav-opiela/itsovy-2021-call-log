package sk.itsovy.android.calllog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import sk.itsovy.android.calllog.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val model : SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.selectedCall.observe(viewLifecycleOwner, ::displayCallInfo)
    }

    fun displayCallInfo(call : Call) {
        binding.detailTextView.text = call.toString()
    }

}