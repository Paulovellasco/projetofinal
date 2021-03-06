package br.iesb.grupo1.projetofinal.util;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.activities.JobDescriptionActivity;
import br.iesb.grupo1.projetofinal.activities.JobListActivity;

/**
 * Created by 1614290072 on 01/06/2018.
 */

public class JobListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtEntidadeConveniadaHolder;
    public TextView txtNomeVagaHolder;
    public String codPosto;
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
        Context context  = v.getContext();
        Intent t = new Intent(context,JobDescriptionActivity.class);
        t.putExtra("codPosto",codPosto);
        Log.d(">>>>>>>>>>>>>>>>",codPosto);
        context.startActivity(t);
    }
}
