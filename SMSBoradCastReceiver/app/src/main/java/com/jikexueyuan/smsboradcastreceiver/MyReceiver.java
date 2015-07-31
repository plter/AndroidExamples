package com.jikexueyuan.smsboradcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION.equals(intent.getAction())) {
            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SmsMessage[] msgs = getMessageFromIntent(intent);
            StringBuilder sBuilder = new StringBuilder();
            if (msgs != null && msgs.length > 0) {
                for (SmsMessage msg : msgs) {
                    sBuilder.append("接收到了短信：\n发件人是：");
                    sBuilder.append(msg.getDisplayOriginatingAddress());
                    sBuilder.append("\n------短信内容-------\n");
                    sBuilder.append(msg.getDisplayMessageBody());
                    i.putExtra("sms_address", msg.getDisplayOriginatingAddress());
                    i.putExtra("sms_body", msg.getDisplayMessageBody());
                }
            }
            context.startActivity(i);
        }

    }

    private SmsMessage[] getMessageFromIntent(Intent intent) {
        SmsMessage retmeMessage[] = null;
        Bundle bundle = intent.getExtras();
        Object pdus[] = (Object[]) bundle.get("pdus");
        retmeMessage = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            byte[] bytedata = (byte[]) pdus[i];
            retmeMessage[i] = SmsMessage.createFromPdu(bytedata);
        }
        return retmeMessage;
    }
}
