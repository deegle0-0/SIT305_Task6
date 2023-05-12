package com.example.task6.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "trucksData")
public class Trucks implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name="model")
    String model;

    @NonNull
    @ColumnInfo(name="date")
    String dates;

    public Trucks(int id, @NonNull String model, @NonNull String dates) {
        this.id = id;
        this.model = model;
        this.dates = dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public  String getDates() {
        return dates;
    }

    public void setDates(@NonNull String dates) {
        this.dates = dates;
    }
}
