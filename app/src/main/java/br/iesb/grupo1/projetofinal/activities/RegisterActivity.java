package br.iesb.grupo1.projetofinal.activities;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import br.iesb.grupo1.projetofinal.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtRegisterEmail;
    private EditText txtRegisterPassword;
    private EditText txtRegisterConfirmPassword;
    private Button btnSubmitRegister;
    String email;
    String password;
    String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        txtRegisterEmail = findViewById(R.id.txtRegisterEmail);
        txtRegisterPassword = findViewById(R.id.txtRegisterPassword);
        txtRegisterConfirmPassword = findViewById(R.id.txtRegisterConfirmPassword);

        btnSubmitRegister = findViewById(R.id.btnSubmitRegister);
        btnSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(txtRegisterEmail != null && txtRegisterEmail.getText() != null)){
                    Toast.makeText(RegisterActivity.this,"Campo Email vazio",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    email = txtRegisterEmail.getText().toString();
                }
                if(!(txtRegisterPassword!=null && txtRegisterPassword.getText() != null)){
                    Toast.makeText(RegisterActivity.this,"Campo Senha",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    password = txtRegisterPassword.getText().toString();
                }
                if (!(txtRegisterConfirmPassword != null && txtRegisterConfirmPassword.getText() != null)){
                    Toast.makeText(RegisterActivity.this,"Campo Confirmação de Senha",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    confirmPassword = txtRegisterConfirmPassword.getText().toString();
                }

                if(!(Objects.equals(password, confirmPassword))){
                    Toast.makeText(RegisterActivity.this,"Campos de Senha Diferem",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    createAccount(email,password);
                }
            }
        });


    }


    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }



    private void createAccount(String email,String password){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference var = db.getReference("teste/");
        var.setValue("login");

        Log.d("Confirm",email+password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!(task.isSuccessful())) {
                            Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent t = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(t);
                        }
                    }
                });
    }
}
