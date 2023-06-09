package com.codestudioapps.learing.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private CardView course1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        course1 = findViewById(R.id.course1);

        course1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código para reproducir el video
                Toast.makeText(ContactActivity.this, "Se hizo clic en la CardView", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactActivity.this, VideoElement.class);
                startActivity(intent);
            }
        });


    }
}