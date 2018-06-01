package br.iesb.grupo1.projetofinal.util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 1614290072 on 01/06/2018.
 */

public interface JobStationService {
    @GET("rest/emprego")
    Call<List<JobStation>> listarEmpregos();
}
