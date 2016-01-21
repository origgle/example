package project.android.my.myredenvelope.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pr0zel on 11/12/15.
 */
@SuppressWarnings("NewApi")
public class NotificationService extends NotificationListenerService {

    final String TAG = "NotiService";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification notification = sbn.getNotification();
        Log.d(TAG, "onNotificationPosted");
        if (null != notification) {
            Bundle extras = notification.extras;
            if (null != extras) {
                List<String> textList = new ArrayList<String>();
                String title = extras.getString("android.title");
                Log.d(TAG, title);
                if (!TextUtils.isEmpty(title)) textList.add(title);

                String detailText = extras.getString("android.text");
                if (!TextUtils.isEmpty(detailText)) textList.add(detailText);

                Log.d(TAG, detailText);
                if (textList.size() > 0) {
                    for (String text : textList) {
                        if (!TextUtils.isEmpty(text) && text.contains("[微信红包]")) {
                            final PendingIntent pendingIntent = notification.contentIntent;
                            try {
                                Log.d(TAG, "Matched 微信红包");
                                pendingIntent.send();
                            } catch (PendingIntent.CanceledException e) {
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }
}