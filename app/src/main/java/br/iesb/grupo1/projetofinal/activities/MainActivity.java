package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import br.iesb.grupo1.projetofinal.R;

public class MainActivity extends AppCompatActivity {

    Button btnGoToEditProfile;
    Button btnLogOut;
    Button btnListJobs;
    ImageView imgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToEditProfile = findViewById(R.id.btnGoToEditProfile);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnGoToEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(t);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        imgMain = findViewById(R.id.imgMain);
        imgMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  t = new Intent(MainActivity.this,ListActivity.class);
                startActivity(t);
            }
        });

        btnListJobs = findViewById(R.id.btnListJobs);
        btnListJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,JobListActivity.class);
                startActivity(t);
            }
        });

    }
}
