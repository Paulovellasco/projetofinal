package br.iesb.grupo1.projetofinal.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.JobStationService;
import retrofit2.Retrofit;


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
                .build();


        jss = retrofitJob.create(JobStationService.class);

    }
}
