package br.iesb.grupo1.projetofinal.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.iesb.grupo1.projetofinal.R;

/**
 * Created by 1614290072 on 01/06/2018.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListViewHolder>{

    private Context contexto;

    private List<JobStation> listaTrabalhos;

    public JobListAdapter(Context contexto , List<JobStation> listaTrabalhos){
        this.contexto = contexto;
        this.listaTrabalhos = listaTrabalhos;
    }

    @Override
    public JobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.vacancy,parent,false);
        return new JobListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, int position) {
        JobStation jStation  = listaTrabalhos.get(position);

        holder.txtEntidadeConveniadaHolder.setText(jStation.getEntidadeConveniada());
        holder.txtNomeVagaHolder.setText((jStation.getNome()));
        holder.jobStation = jStation;

    }

    @Override
    public int getItemCount() {
        return listaTrabalhos.size();
    }
}
