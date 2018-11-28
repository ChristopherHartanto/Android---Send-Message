package com.example.test.pertemuan8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = null;

        Object[] objects = (Object[]) bundle.get("pdus");
        smsMessages = new SmsMessage[objects.length];

        String message = "";
        for (int i = 0; i < objects.length; i++) {
            smsMessages[i] = SmsMessage.createFromPdu((byte[])objects[i], bundle.getString("format"));
            message += smsMessages[i].getMessageBody().toString();
        }

        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        newIntent.putExtra("message", message);
        context.startActivity(newIntent);

    }
}