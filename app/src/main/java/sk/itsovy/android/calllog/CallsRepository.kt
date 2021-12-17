package sk.itsovy.android.calllog

import android.content.Context
import android.database.ContentObserver
import android.provider.CallLog
import androidx.lifecycle.LiveData

class CallsRepository(private val context: Context) {

    val liveDataCalls: LiveData<List<Call>> = object : LiveData<List<Call>>() {
        lateinit var observer: ContentObserver

        override fun onActive() {
            observer = object : ContentObserver(null) {
                // toto sa zavola ked sa zmenia data - v nasom pripade udaje o hovoroch
                override fun onChange(selfChange: Boolean) {
                    postValue(loadCalls())
                }
            }

            context.contentResolver.registerContentObserver(CallLog.Calls.CONTENT_URI, true, observer)
            observer.onChange(true)
        }

        override fun onInactive() {
            context.contentResolver.unregisterContentObserver(observer)
        }

    }


    // 1x nacita data z tabulky
    fun loadCalls(): List<Call> {
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            CallLog.Calls.CONTENT_URI, null, null, null, null
        ) ?: return emptyList()
        val calls = mutableListOf<Call>()
        if (cursor.count > 0) {
            val numberIdx = cursor.getColumnIndex(CallLog.Calls.NUMBER)
            val typeIdx = cursor.getColumnIndex(CallLog.Calls.TYPE)
            while (cursor.moveToNext()) {
                val number = cursor.getString(numberIdx)
                val type = cursor.getInt(typeIdx)
                calls.add(Call(number, type))
            }
        }
        cursor.close()
        return calls
    }

}