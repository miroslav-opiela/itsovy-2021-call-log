package sk.itsovy.android.calllog

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import sk.itsovy.android.calllog.databinding.FragmentMasterBinding

class MasterFragment : Fragment(), OnNumberClickListener {

    private lateinit var binding: FragmentMasterBinding
    private val model: SharedViewModel by activityViewModels()
    private val callModel: CallsViewModel by activityViewModels() {
        CallsViewModel.CallsViewModelFactory((requireActivity().application as CallApplication).repository)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                init()
            } else {
                Toast.makeText(requireContext(), "Permission missing", Toast.LENGTH_SHORT).show()
            }
        }

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

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CALL_LOG
            ) == PackageManager.PERMISSION_GRANTED ->
                init()

            shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG) -> {
                val alertDialog: AlertDialog = requireActivity().let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setTitle("Permissions")
                        setPositiveButton("OK") { _, _ ->
                            requestPermissionLauncher.launch(Manifest.permission.READ_CALL_LOG)
                        }
                        setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }
                    builder.create()
                }
                alertDialog.show()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_CALL_LOG)
            }

        }


    }

    private fun init() {
        val adapter = CallLogAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        callModel.calls.observe(this, {
            adapter.submitList(it)
        })
    }

    override fun onNumberClick(call: Call) {
        // oslovim view model a zmenim hodnotu live dat
        model.select(call)
    }

}

interface OnNumberClickListener {
    fun onNumberClick(call: Call)
}