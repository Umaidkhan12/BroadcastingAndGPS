package com.example.broadcastingandgps

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val state: Boolean = intent?.getBooleanExtra("state", false) ?: return
        if (state) t(context, "Air On") else t(context, "Air Off")

    }

    private fun t(context: Context?, msg: Any) {
        Toast.makeText(context, "$msg", Toast.LENGTH_SHORT).show()
    }
}