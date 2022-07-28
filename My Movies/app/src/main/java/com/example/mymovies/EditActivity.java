package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {



    Button btnUpdate, btnDelete,btnCancel;
    EditText etTitle, etGenre,etYear,etID;
    Spinner spinner;
    String rating;
    DBHelper db=new DBHelper(EditActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID=findViewById(R.id.editTextMovieID);
        btnCancel=findViewById(R.id.bCancel);
        btnUpdate =findViewById(R.id.bUpdate);
        btnDelete =findViewById(R.id.bDelete);
        etGenre=findViewById(R.id.etGenre);
        spinner=findViewById(R.id.spinner);
        etTitle=findViewById(R.id.etTitle);
        etYear=findViewById(R.id.etYear);


        Intent i = getIntent();
        Movies movie = (Movies) i.getSerializableExtra("Selected");
        etID.setText(movie.getId()+"");
        etID.setEnabled(false);
        etTitle.setText(movie.getTitle());
        etGenre.setText(movie.getGenre());
        etYear.setText(movie.getYear()+"");

        int count=spinner.getCount();
        for(int a=0;a<count;a++)
        {
            String rating=spinner.getSelectedItem().toString();
            if(rating.equalsIgnoreCase(movie.getRating()))
            {
                spinner.setSelection(a);
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movie.updateMovieDetails(etTitle.getText().toString(),etGenre.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()),spinner.getSelectedItem().toString());
                db.updateMovie(movie);
                db.close();
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(EditActivity.this);
                db.deleteMovie(movie.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}