package sk.itsovy.android.calllog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sk.itsovy.android.calllog.databinding.FragmentMasterBinding

class MasterFragment : Fragment() {

    lateinit var binding: FragmentMasterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMasterBinding.inflate(inflater)
        return binding.root
    }

}