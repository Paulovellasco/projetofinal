package br.iesb.grupo1.projetofinal.util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 1614290072 on 01/06/2018.
 */

public interface JobStationService {
    @GET("rest/emprego")
    Call<List<JobStation>> listarEmpregos();


    @GET("rest/emprego/cod/{codPosto}")
    Call<List<JobStation>> listaVaga(@Path("codPosto")String codPosto);
}
