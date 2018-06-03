package br.iesb.grupo1.projetofinal.util;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.iesb.grupo1.projetofinal.R;

/**
 * Created by 1614290072 on 01/06/2018.
 */

public class JobListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtEntidadeConveniadaHolder;
    public TextView txtNomeVagaHolder;
    public JobStation jobStation;

    public JobListViewHolder(View itemView) {
        super(itemView);

        this.txtEntidadeConveniadaHolder = itemView.findViewById(R.id.txtEntidadeConveniada);
        this.txtNomeVagaHolder           = itemView.findViewById(R.id.txtNomeVaga);
        itemView.setOnClickListener(this);


        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        params.setMargins(35, 0, 0, 0);
        itemView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        //TODO no clique da vaga
    }
}
