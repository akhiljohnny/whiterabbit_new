package com.akhil.whiterabittest.RoomDatabase;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.akhil.whiterabittest.model.EmployeeRoomModel;


@Dao
public interface EmployeeDao {

    @Query("SELECT * from EmployeeRoomModel WHERE id=:id")
    EmployeeRoomModel getEmployeeDetails(String id);

    @Query("SELECT * from EmployeeRoomModel WHERE name=:name OR email=:name")
    Cursor getEmployeeDetailsByName(String name);

    @Query("SELECT * from EmployeeRoomModel")
    Cursor getAllEmployeeDetails();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeRoomModel vehicleManual);

    @Update
    void update(EmployeeRoomModel vehicleManual);

    @Query("DELETE FROM EmployeeRoomModel WHERE id = :id")
    void delete(String id);

    @Query("DELETE FROM EmployeeRoomModel")
    void deleteAll();
}