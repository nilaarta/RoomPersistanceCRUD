package com.example.roompersistancecrud;

import android.app.Application;
import android.icu.lang.UCharacter;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class PhonebookRepository {
    private PhonebookDAO phonebookDAO;
    private LiveData<List<Phonebook>> allPhonebook;

    public PhonebookRepository(Application application){
        PhonebookDatabase database = PhonebookDatabase.getInstance(application);
        phonebookDAO = database.phonebookDAO();
        allPhonebook = phonebookDAO.getAllPhonebook();
    }

    public void insert(Phonebook phonebook){
        new InsertPhonebookAsyncTask(phonebookDAO).execute(phonebook);
    }

    public void update (Phonebook phonebook){
        new UpdatePhonebookAsyncTask(phonebookDAO).execute(phonebook);
    }

    public void delete (Phonebook phonebook){
        new DeletePhonebookAsyncTask(phonebookDAO).execute(phonebook);
    }

    public void deleteAllPhonebook(){
        new DeleteAllPhonebookAsyncTask(phonebookDAO).execute();
    }

    public LiveData<List<Phonebook>> getAllPhonebook(){
        return allPhonebook;
    }

    private static class InsertPhonebookAsyncTask extends AsyncTask<Phonebook, Void, Void>{
        private PhonebookDAO phonebookDAO;

        private InsertPhonebookAsyncTask(PhonebookDAO phonebookDAO){
            this.phonebookDAO = phonebookDAO;
        }

        @Override
        protected Void doInBackground(Phonebook... phonebooks) {
            phonebookDAO.insert(phonebooks[0]);
            return null;
        }
    }

    private static class UpdatePhonebookAsyncTask extends AsyncTask<Phonebook, Void, Void>{
        private PhonebookDAO phonebookDAO;

        private UpdatePhonebookAsyncTask(PhonebookDAO phonebookDAO){
            this.phonebookDAO = phonebookDAO;
        }

        @Override
        protected Void doInBackground(Phonebook... phonebooks) {
            phonebookDAO.update(phonebooks[0]);
            return null;
        }
    }

    private static class DeletePhonebookAsyncTask extends AsyncTask<Phonebook, Void, Void>{
        private PhonebookDAO phonebookDAO;

        private DeletePhonebookAsyncTask(PhonebookDAO phonebookDAO){
            this.phonebookDAO = phonebookDAO;
        }

        @Override
        protected Void doInBackground(Phonebook... phonebooks) {
            phonebookDAO.delete(phonebooks[0]);
            return null;
        }
    }

    private static class DeleteAllPhonebookAsyncTask extends AsyncTask<Phonebook, Void, Void>{
        private PhonebookDAO phonebookDAO;

        private DeleteAllPhonebookAsyncTask(PhonebookDAO phonebookDAO){
            this.phonebookDAO = phonebookDAO;
        }

        @Override
        protected Void doInBackground(Phonebook... phonebooks) {
            phonebookDAO.deleteAllPhobnebook();
            return null;
        }
    }
}
