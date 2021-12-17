package sk.itsovy.android.calllog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CallsViewModel(private val repository: CallsRepository) : ViewModel() {

    fun loadCalls() : List<Call> = repository.loadCalls()

    class CallsViewModelFactory(private val repository: CallsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CallsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CallsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}