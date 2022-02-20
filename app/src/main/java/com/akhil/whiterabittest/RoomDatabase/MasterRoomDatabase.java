package com.akhil.whiterabittest.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.akhil.whiterabittest.model.EmployeeRoomModel;


@Database(entities = {EmployeeRoomModel.class}, version = 2, exportSchema = false)
public abstract class MasterRoomDatabase extends RoomDatabase {
    public static MasterRoomDatabase instance;


    public abstract EmployeeDao employeeDao();

    public static MasterRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (MasterRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            MasterRoomDatabase.class, "vehicleManualDb")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDatabaseAsync(instance).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final EmployeeDao employeeDao;


        public PopulateDatabaseAsync(MasterRoomDatabase database) {
            employeeDao = database.employeeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
