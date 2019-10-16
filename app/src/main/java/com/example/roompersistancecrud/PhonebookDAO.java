package com.example.roompersistancecrud;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhonebookDAO {

    @Insert
    void insert(Phonebook phonebook);

    @Delete
    void delete(Phonebook phonebook);

    @Update
    void  update (Phonebook phonebook);

    @Query ("DELETE FROM phonebook_tables")
    void deleteAllPhobnebook();

    @Query("SELECT * FROM phonebook_tables ORDER BY nama ASC")
    LiveData<List<Phonebook>> getAllPhonebook();

}
