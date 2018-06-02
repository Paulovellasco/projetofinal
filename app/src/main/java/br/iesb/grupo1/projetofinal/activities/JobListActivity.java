package br.iesb.grupo1.projetofinal.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.iesb.grupo1.projetofinal.R;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        retrofitJob = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jss = retrofitJob.create(JobStationService.class);

        Call<List<JobStation>> listaEstacoesTrabalho = jss.listarEmpregos();


        listaEstacoesTrabalho.enqueue(new Callback<List<JobStation>>() {
            @Override
            public void onResponse(Call<List<JobStation>> call, Response<List<JobStation>> response) {
                if(response.isSuccessful()){
                    List<JobStation> lista = response.body();
                    if(lista != null && lista.size()>0){
                        for (JobStation j : lista) {

                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<List<JobStation>> call, Throwable t) {

            }
        });

    }



}
