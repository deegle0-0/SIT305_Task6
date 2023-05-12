package com.example.task6.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.task6.data.Trucks;
import com.example.task6.data.TrucksDAO;
import com.example.task6.data.TrucksRoomDatabase;

import java.util.List;

public class TrucksRepository {

    TrucksDAO trucksDAO;
    LiveData<List<Trucks>> trucksAvail;

    public TrucksRepository(Application application)
    {
        TrucksRoomDatabase db = TrucksRoomDatabase.getDatabase(application);
        trucksDAO = db.trucksDAO();
        trucksAvail = trucksDAO.getTrucks();
    }

    public LiveData<List<Trucks>> getTrucks(){return trucksAvail;}

    public void insert(Trucks trucks){
        TrucksRoomDatabase.databaseWriteExecutor.execute(()->{
            trucksDAO.insert(trucks);
        });
    }

    public void update(Trucks trucks){
        TrucksRoomDatabase.databaseWriteExecutor.execute(()->{
            trucksDAO.update(trucks);
        });
    }


    public void delete(Trucks trucks){
        TrucksRoomDatabase.databaseWriteExecutor.execute(()->{
            trucksDAO.delete(trucks);
        });
    }
}
