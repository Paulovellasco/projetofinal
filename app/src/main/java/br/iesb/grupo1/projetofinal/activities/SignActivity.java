package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.iesb.grupo1.projetofinal.R;

public class SignActivity extends AppCompatActivity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        btnReturn = findViewById(R.id.btnSignReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(SignActivity.this,JobListActivity.class);
                startActivity(t);
                finish();
            }
        });

    }
}
