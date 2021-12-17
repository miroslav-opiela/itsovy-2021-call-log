package sk.itsovy.android.calllog

import android.app.Application

class CallApplication : Application() {

    val repository by lazy {CallsRepository(this)}
}