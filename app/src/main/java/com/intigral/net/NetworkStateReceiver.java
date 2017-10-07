package com.intigral.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.intigral.IntigralApplication;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    private Boolean mIsConnected;

    public NetworkStateReceiver() {
    }

    public void onReceive(@NonNull Context context, @Nullable Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        mIsConnected = networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;

        notifyStateToAll();
    }
   //method to notify the network connected and disconnected status
    private void notifyStateToAll() {
        if (mIsConnected != null && mIsConnected) {
           // Log.d("mIsConnected",mIsConnected.toString());
            IntigralApplication.getEventBus().post(new NetworkConnectedEvent());
        }else if(mIsConnected != null && ! mIsConnected ){
           // Log.d("mIsConnected",mIsConnected.toString());
            IntigralApplication.getEventBus().post(new NetworkDisConnectedEvent());
        }
    }

    public class NetworkConnectedEvent {
    }
    public class NetworkDisConnectedEvent {
    }
}
