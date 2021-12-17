package sk.itsovy.android.calllog

import android.content.Context
import android.provider.CallLog

class CallsRepository(private val context: Context) {

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