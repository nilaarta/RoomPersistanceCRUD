package com.example.roompersistancecrud;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Phonebook.class}, version = 2, exportSchema = false)
public abstract class PhonebookDatabase extends RoomDatabase {

    private static PhonebookDatabase instance;

    public abstract PhonebookDAO phonebookDAO();

    public static synchronized PhonebookDatabase getInstance(Context context){
        if (instance == null ){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PhonebookDatabase.class, "phonebook_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private PhonebookDAO phonebookDAO;

        private PopulateDbAsyncTask(PhonebookDatabase db){
            phonebookDAO = db.phonebookDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            phonebookDAO.insert(new Phonebook("nila", "bali", "123", "diri"));
            return null;
        }
    }
 }
