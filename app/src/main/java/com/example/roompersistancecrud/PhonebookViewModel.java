package com.example.roompersistancecrud;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhonebookViewModel extends AndroidViewModel {
    private PhonebookRepository repository;
    private LiveData<List<Phonebook>> allPhonebook;

    public PhonebookViewModel(@NonNull Application application) {
        super(application);
        repository = new PhonebookRepository(application);
        allPhonebook = repository.getAllPhonebook();
    }

    public void insert (Phonebook phonebook){
        repository.insert(phonebook);
    }

    public void update(Phonebook phonebook){
        repository.update(phonebook);
    }

    public void delete(Phonebook phonebook){
        repository.delete(phonebook);
    }

    public void deleteAllPhonebook(Phonebook phonebook){
        repository.deleteAllPhonebook();
    }

    public LiveData<List<Phonebook>> getAllPhonebook(){
        return allPhonebook;
    }
}
