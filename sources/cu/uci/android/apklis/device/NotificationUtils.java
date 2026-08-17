package cu.uci.android.apklis.device;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.activity.HomeActivity;
import cu.uci.android.apklis.presentation.ui.activity.MainActivity;

/* loaded from: classes.dex */
public class NotificationUtils extends ContextWrapper {
    public static final String ANDROID_CHANNEL_ID = "cu.uci.android.apklis.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    private NotificationManager mManager;

    public NotificationUtils(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= 26) {
            createChannels();
        }
    }

    @RequiresApi(api = 26)
    public static Notification toServiceFor8(String str, String str2, Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 0);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification build = new Notification.Builder(context, str).setOngoing(true).setSmallIcon(R.drawable.ic_logo_apklis).setCategory(NotificationCompat.CATEGORY_SERVICE).build();
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.setFlags(603979776);
        build.contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return build;
    }

    @RequiresApi(api = 26)
    public void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, 3);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(-16711936);
        notificationChannel.setLockscreenVisibility(0);
        getManager().createNotificationChannel(notificationChannel);
    }

    public void createNotification(int i, String str) {
        Intent intent = new Intent(Utils.getContext(), (Class<?>) HomeActivity.class);
        intent.putExtra("id_intent_notf", 1564);
        intent.setFlags(603979776);
        try {
            Notification build = new Notification.Builder(Utils.getContext()).setContentTitle(getString(R.string.app_notif_update, new Object[]{String.valueOf(i)})).setContentText(str).setSmallIcon(R.drawable.ic_logo_apklis).setContentIntent(PendingIntent.getActivity(Utils.getContext(), (int) System.currentTimeMillis(), intent, 0)).build();
            NotificationManager notificationManager = (NotificationManager) Utils.getContext().getSystemService("notification");
            build.flags |= 16;
            notificationManager.notify(102, build);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = 26)
    public void createNotification8(int i, String str) {
        Intent intent = new Intent(Utils.getContext(), (Class<?>) HomeActivity.class);
        intent.putExtra("id_intent_notf", 1564);
        intent.setFlags(603979776);
        getManager().notify(101, getAndroidChannelNotification(getString(R.string.app_notif_update, new Object[]{String.valueOf(i)}), str).setContentIntent(PendingIntent.getActivity(Utils.getContext(), (int) System.currentTimeMillis(), intent, 0)).setBadgeIconType(R.drawable.ic_logo_apklis).setSmallIcon(R.drawable.ic_logo_apklis).build());
    }

    @RequiresApi(api = 26)
    public Notification.Builder getAndroidChannelNotification(String str, String str2) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_logo_apklis).setAutoCancel(true);
    }

    public NotificationManager getManager() {
        if (this.mManager == null) {
            this.mManager = (NotificationManager) getSystemService("notification");
        }
        return this.mManager;
    }
}
