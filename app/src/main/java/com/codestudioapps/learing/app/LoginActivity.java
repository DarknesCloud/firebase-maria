package com.codestudioapps.learing.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsernameEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsernameEmail = findViewById(R.id.editTextTextPersonName);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos los valores ingresados por el usuario
                final String usernameEmail = editTextUsernameEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

                // Realizamos la consulta a Firebase
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
                usersRef.orderByChild("email").equalTo(usernameEmail).limitToFirst(1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    String dbPassword = userSnapshot.child("password").getValue(String.class);
                                    if (dbPassword != null && dbPassword.equals(password)) {
                                        // Credenciales válidas, el usuario puede ingresar.
                                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                                        // Aquí puedes navegar a la siguiente actividad o realizar las acciones necesarias para el inicio de sesión exitoso.
                                        // Por ejemplo, puedes usar un Intent para abrir otra actividad:
                                        // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        // startActivity(intent);
                                    } else {
                                        // Contraseña inválida, mostramos un mensaje de error.
                                        Toast.makeText(LoginActivity.this, "Contraseña inválida, por favor inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                // No se encontró el correo electrónico en la base de datos, mostramos un mensaje de error.
                                Toast.makeText(LoginActivity.this, "Correo electrónico no encontrado, por favor inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error al realizar la consulta, mostramos un mensaje de error.
                            Toast.makeText(LoginActivity.this, "Error al conectar a la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
