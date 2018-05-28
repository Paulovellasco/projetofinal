package br.iesb.grupo1.projetofinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.Job;
import br.iesb.grupo1.projetofinal.util.JobStation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class JobListActivity extends AppCompatActivity {

    //INTERFACE
    public interface JobStationService{
        @GET("/rest/emprego")
        Call<List<JobStation>> listarPostos();
    }

    JobStationService jService;
    String API_BASE_URL = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        Retrofit retrofitJob = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .build();


        jService = retrofitJob.create(JobStationService.class);
        Call<List<JobStation>> requisition = jService.listarPostos();
        requisition.enqueue(new Callback<List<JobStation>>() {
            @Override
            public void onResponse(Call<List<JobStation>> call, Response<List<JobStation>> response) {
                if(response.isSuccessful()){
                    List<JobStation> list = response.body();

                    if(list != null && list.size() != 0){
                        JobStation j = list.get(0);
                        Log.d("A", "<<<<<<<<<<<<<<<<<< Posto n: " + j.codPosto + " >>>>>>>>>>>>>>>>>>>>>>>>>");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JobStation>> call, Throwable t) {
                Toast.makeText(JobListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
