package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.iesb.grupo1.projetofinal.R;

public class ProfileActivity extends AppCompatActivity {

    TextView txProfileEmail;
    TextView txProfileName;
    String currentUserStoredEmail;

    Button btnSaveProfile;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //GET INFO FROM INTENT
        Bundle b = getIntent().getExtras();
        if(b != null) {
            currentUserStoredEmail = b.getString("email");
        }

        //LOAD COMPONENTS FROM XML
        txProfileEmail = findViewById(R.id.txProfileEmailField);
        txProfileEmail.setText(currentUserStoredEmail);
        txProfileName = findViewById(R.id.txNameProfileField);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        //SET LISTENER
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txProfileEmail == null || txProfileName == null) {
                    return;
                }else{
                    if(txProfileEmail.getText() == null || txProfileName == null){
                        return;
                    }else{
                        saveProfileData(txProfileName.getText().toString());
                    }
                }
            }
        });
    }

    public void saveProfileData(String name){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("/users");

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(txProfileName.getText().toString());
        Task t = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(txProfileEmail.getText().toString());

        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(i);

    }
}
