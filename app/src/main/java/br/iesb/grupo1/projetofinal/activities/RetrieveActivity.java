package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import br.iesb.grupo1.projetofinal.R;

public class RetrieveActivity extends AppCompatActivity {

    private EditText txtRetrieveEmail;
    private Button btnRetrieve;
    private  String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);


        btnRetrieve = findViewById(R.id.btnRetrieve);
        txtRetrieveEmail = findViewById(R.id.txtRetrieveEmail);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            //TODO verificar se existe o email a ser recuperado existe no banco de dados
            public void onClick(View v) {



                if((txtRetrieveEmail == null || txtRetrieveEmail.getText() == null)){
                    Toast.makeText(RetrieveActivity.this,"Campo Email Vazio",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    email = txtRetrieveEmail.getText().toString();
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent t = new Intent(RetrieveActivity.this,LoginActivity.class);
                                    startActivity(t);
                                }
                            });
                }
            }
        });

    }
}
