package br.iesb.grupo1.projetofinal.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
    UserRecycleViewAdapter rva;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = FirebaseDatabase.getInstance(); // pega uma instancia do banco
        dr = db.getReference("/recicleView"); //pega uma referencia do no
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){  //para cada item do no
                    UserTest usuario = ds.getValue(UserTest.class);
                    userArray.add(usuario);


                }

                rva.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rva = new UserRecycleViewAdapter(this,userArray);
        rv = findViewById(R.id.testRecycle);
        rv.setAdapter(rva);
        //rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this));




    }

    class UserViewHolder extends RecyclerView.ViewHoder implements View.OnClickListener {

        public TextView txtRecycleName;
        public TextView txtRecycleAlias;
        public UserTest user;

        public UserViewHolder(View itemView) {
            super(itemView);

            this.txtRecycleName = itemView.findViewById(R.id.txtRecycleName);
            this.txtRecycleAlias = itemView.findViewById(R.id.txtRecycleAlias);
            itemView.setOnClickListener(this);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.setMargins(35, 0, 0, 0);
            itemView.setLayoutParams(params);

        }

        @Override
        public void onClick(View v) {
            //TODO no clique o card do user faz algo
        }
    }

    class UserRecycleViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private Context contexto;
        private List<UserTest> listaUsuarios;

        public UserRecycleViewAdapter(Context contexto, List<UserTest> listaUsuarios) {
            this.contexto = contexto;
            this.listaUsuarios = listaUsuarios;
        }

        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(contexto).inflate(R.layout.user, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            UserTest user = listaUsuarios.get(position);

            holder.txtRecycleName.setText(user.getName());
            holder.txtRecycleAlias.setText(user.getEmail());
            holder.user = user;
        }

        @Override
        public int getItemCount() {
            return listaUsuarios.size();
        }
    }
}
