package br.iesb.grupo1.projetofinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.Job;
import retrofit2.Retrofit;

public class JobListActivity extends AppCompatActivity {

    Job.JobService jService;
    String API_BASE_URL = "https://api.catho.com.br/vagas/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        Retrofit retrofitJob = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .build();


        jService = retrofitJob.create(Job.JobService.class);
        Log.d("-------------------->",jService.toString());


    }


    //adapter

    class JobListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public JobListHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }


    //view holder
}
