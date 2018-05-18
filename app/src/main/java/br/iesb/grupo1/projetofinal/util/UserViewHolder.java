package br.iesb.grupo1.projetofinal.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.iesb.grupo1.projetofinal.R;

/**
 * Created by 1614290072 on 17/05/2018.
 */

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtRecycleName;
    public TextView txtRecycleAlias;
    public UserTest user;

    public UserViewHolder(View itemView) {
        super(itemView);

        this.txtRecycleName =  itemView.findViewById(R.id.txtRecycleName);
        this.txtRecycleAlias = itemView.findViewById(R.id.txtRecycleAlias);
        itemView.setOnClickListener(this);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        params.setMargins(35,0,0,0);
        itemView.setLayoutParams(params);

    }

    @Override
    public void onClick(View v) {
        //TODO no clique o card do user faz algo
    }
}
