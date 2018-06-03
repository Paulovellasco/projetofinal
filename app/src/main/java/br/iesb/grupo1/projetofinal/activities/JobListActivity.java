package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.JobListAdapter;
import br.iesb.grupo1.projetofinal.util.JobListViewHolder;
import br.iesb.grupo1.projetofinal.util.JobStation;
import br.iesb.grupo1.projetofinal.util.JobStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JobListActivity extends AppCompatActivity {

    JobStationService jss;
    Retrofit retrofitJob;
    String URL_BASE = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/" ;

    private ImageView userImg;
    private TextView userName;
    private ArrayList<JobStation> jobStationArray = new ArrayList<>();

    private JobListAdapter ja;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        userImg = findViewById(R.id.userImg);
        userImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(JobListActivity.this, ProfileActivity.class));
            }
        });


        retrofitJob = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jss = retrofitJob.create(JobStationService.class);

        Call<List<JobStation>> listaEstacoesTrabalho = jss.listarEmpregos();


//        JobListAdapter  jobAdapter = new JobListAdapter(this,jobStationArray);
//        RecyclerView jobRecycle = findViewById(R.id.jobRecicleView);
//        jobRecycle.setAdapter(ja);
//        jobRecycle.setLayoutManager(new LinearLayoutManager(this));




        listaEstacoesTrabalho.enqueue(new Callback<List<JobStation>>() {
            @Override
            public void onResponse(Call<List<JobStation>> call, Response<List<JobStation>> response) {
                if(response.isSuccessful()){
                    List<JobStation> lista = response.body();
                    if(lista != null && lista.size()>0){
                        for (JobStation j : lista) {
                            jobStationArray.add(j);
                            System.out.println(jobStationArray);


                        }
                        mostraDados(jobStationArray);
                    }
                }


            }

            @Override
            public void onFailure(Call<List<JobStation>> call, Throwable t) {

            }
        });



    }

    public void mostraDados(ArrayList<JobStation> arrayJob ){
        RecyclerView rv = findViewById(R.id.jobRecicleView);
        rv.setAdapter(new JobListAdapter(this,arrayJob));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}
