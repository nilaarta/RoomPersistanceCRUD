package com.example.roompersistancecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_PHONEBOOK_REQUEST = 1;
    private  PhonebookViewModel phonebookViewModel;
    PhonebookDAO phonebookDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddPhonebook = findViewById(R.id.bottom_add);
        buttonAddPhonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPhonebookActivity.class);
                startActivityForResult(intent, ADD_PHONEBOOK_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PhonebookAdapter adapter = new PhonebookAdapter();
        recyclerView.setAdapter(adapter);

        phonebookViewModel = ViewModelProviders.of(this).get(PhonebookViewModel.class);
        phonebookViewModel.getAllPhonebook().observe(this, new Observer<List<Phonebook>>() {
            @Override
            public void onChanged(List<Phonebook> phonebooks) {
                adapter.setPhonebooks(phonebooks);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_PHONEBOOK_REQUEST && resultCode == RESULT_OK){
            String nama = data.getStringExtra(AddPhonebookActivity.EXTRA_NAMA);
            String nohp = data.getStringExtra(AddPhonebookActivity.EXTRA_NOHP);
            String alamat = data.getStringExtra(AddPhonebookActivity.EXTRA_ALAMAT);
            String hubungan = data.getStringExtra(AddPhonebookActivity.EXTRA_HUBUNGAN);

            Phonebook phonebook = new Phonebook(nama, nohp, alamat, hubungan);
            phonebookViewModel.insert(phonebook);

            Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Phonebook not save", Toast.LENGTH_SHORT).show();
        }
    }
}
