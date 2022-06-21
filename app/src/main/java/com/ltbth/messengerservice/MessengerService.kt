package com.ltbth.messengerservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

private const val MSG_SAY_HELLO = 1

class MessengerService : Service() {
    private lateinit var mMessenger: Messenger

    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO ->
                    Toast.makeText(applicationContext,"hello!",Toast.LENGTH_SHORT).show()
                else ->
                    super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext,"binding",Toast.LENGTH_SHORT).show()
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }
}