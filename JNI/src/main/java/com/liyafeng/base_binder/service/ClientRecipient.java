package com.liyafeng.base_binder.service;

import android.os.IBinder;

import com.liyafeng.base_log.KLog.api.KLog;

public class ClientRecipient implements IBinder.DeathRecipient {

    private String pacakgeName;

    public ClientRecipient(String pacakgeName) {
        this.pacakgeName = pacakgeName;
    }

    @Override
    public void binderDied() {
        KLog.e("ClientRecipient", "binderDied() | client bidner died: " + pacakgeName);
    }

    public String getPacakgeName() {
        return pacakgeName;
    }
}
