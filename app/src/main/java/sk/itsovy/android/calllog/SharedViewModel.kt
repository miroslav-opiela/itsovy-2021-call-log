package sk.itsovy.android.calllog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val selectedCall = MutableLiveData<Call>()

    fun select(call: Call) {
        selectedCall.value = call
    }

}