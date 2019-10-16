package com.example.roompersistancecrud;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phonebook_tables" )
public class Phonebook {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nama;

    private String alamat;

    private String nohp;

    private String hubungan;

    public Phonebook(String nama, String alamat, String nohp, String hubungan) {
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.hubungan = hubungan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public String getHubungan() {
        return hubungan;
    }
}
