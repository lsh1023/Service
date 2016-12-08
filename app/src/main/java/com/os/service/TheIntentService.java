package com.os.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by LSH on 2016/12/8.
 *
 */

public class TheIntentService extends IntentService {

    public TheIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //在这里执行耗时操作
    }
}
