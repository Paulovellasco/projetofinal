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
 * Created by 1614290072 on 17/05/2018.
 */

public class UserRecycleViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private Context contexto;
    private List<UserTest> listaUsuarios;

    public UserRecycleViewAdapter(Context contexto, List<UserTest> listaUsuarios) {
        this.contexto = contexto;
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserTest user = listaUsuarios.get(position);

        holder.txtRecycleName.setText(user.getName());
        holder.txtRecycleAlias.setText(user.getAlias());
        holder.user = user;
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
}
