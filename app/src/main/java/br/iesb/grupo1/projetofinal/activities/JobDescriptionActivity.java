package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.JobStation;
import br.iesb.grupo1.projetofinal.util.JobStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobDescriptionActivity extends AppCompatActivity{

    String codPosto;
    TextView txtNome;
    TextView txtEntidade;
    TextView txtEndereco;
    TextView txtBairro;
    TextView txtCep;
    TextView txtTelefone;
    TextView txtMunicipio;
    TextView txtUf;
    Double lat;
    Double lng;

    Retrofit descricao;
    String URL_BASE = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/" ;
    JobStationService jss;

    ImageView imgMap;
    ImageView imgSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);

        txtNome = findViewById(R.id.txtNomeDescricao);
        txtEntidade = findViewById(R.id.txtEntidadeDescricao);
        txtEndereco = findViewById(R.id.txtEnderecoDescricao);
        txtBairro = findViewById(R.id.txtBairroDescricao);
        txtCep = findViewById(R.id.txtCepDescricao);
        txtTelefone = findViewById(R.id.txtTelefoneDescricao);
        txtMunicipio = findViewById(R.id.txtMunicipioDescricao);
        txtUf = findViewById(R.id.txtUfDescricao);

        Bundle b = getIntent().getExtras();
        if (b == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            codPosto = b.getString("codPosto");
        }


        descricao = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jss = descricao.create(JobStationService.class);
        Call<List<JobStation>> vaga = jss.listaVaga(codPosto);
        vaga.enqueue(new Callback<List<JobStation>>() {
            @Override
            public void onResponse(Call<List<JobStation>> call, Response<List<JobStation>> response) {
                if (!response.isSuccessful()) {

                } else {
                    List<JobStation> lista = response.body();
                    if (lista != null && lista.size() > 0) {
                        for (JobStation j : lista) {
                            txtNome.setText(j.getNome());
                            txtEntidade.setText(j.getEntidadeConveniada());
                            txtEndereco.setText(j.getEndereco());
                            txtBairro.setText(j.getBairro());
                            txtCep.setText(j.getCep());
                            txtTelefone.setText(j.getTelefone());
                            txtMunicipio.setText(j.getMunicipio());
                            txtUf.setText(j.getUf());
                            lat = j.getLat();
                            lng = j.getLongitude();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JobStation>> call, Throwable t) {

            }
        });


        imgMap = findViewById(R.id.imgMap);
        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(JobDescriptionActivity.this,JobMapActivity.class);
                t.putExtra("lat",lat);
                t.putExtra("lng",lng);
                startActivity(t);
            }
        });
        imgSign = findViewById(R.id.imgSign);
        imgSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(JobDescriptionActivity.this,SignActivity.class);
                startActivity(t);
            }
        });

    }
}
