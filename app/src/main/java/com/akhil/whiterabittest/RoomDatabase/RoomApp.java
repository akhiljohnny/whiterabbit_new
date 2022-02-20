package com.akhil.whiterabittest.RoomDatabase;

import android.app.Application;

public class RoomApp extends Application {

    private static RoomApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    /**
     * Get the Application Context
     *
     * @return {@code Context}
     */
    public static RoomApp getInstance() {
        return instance;
    }
}
