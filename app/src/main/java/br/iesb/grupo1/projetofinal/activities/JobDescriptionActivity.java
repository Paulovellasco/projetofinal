package br.iesb.grupo1.projetofinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.JobStation;
import br.iesb.grupo1.projetofinal.util.JobStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobDescriptionActivity extends AppCompatActivity implements OnMapReadyCallback {

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


    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

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
        if(b==null){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
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
                if(!response.isSuccessful()){

                }else{
                    List<JobStation> lista = response.body();
                    if(lista != null && lista.size()>0){
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
                            lat = j.getLongitude();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JobStation>> call, Throwable t) {

            }
        });

        //parte do mapa--->

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapDescricao);
//        mapFragment.getMapAsync(JobDescriptionActivity.this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng local = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(local).title("Emprego aqui !"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));
    }
}
