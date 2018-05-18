package br.iesb.grupo1.projetofinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.UserTest;

public class ListActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference dr;
    UserTest user;
    ArrayList <UserTest> userArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = FirebaseDatabase.getInstance();
        dr = db.getReference("/recicleView");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    UserTest usuario = ds.getValue(UserTest.class);
                    userArray.add(usuario);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    private void getData(){


    }
}
