package erico.fipeapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import erico.fipeapp.business.Marca;

import java.util.List;

public class MarcaAdapter extends RecyclerView.Adapter<MarcaAdapter.ViewHolder> {

    private List<Marca> marcaList;

    public MarcaAdapter(List<Marca> items) {
        marcaList = items;
    }

    public void setData(List<Marca> items) {
        marcaList = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marca_list_item, parent, false);
        Log.d("ADAPTER", "VIEW "+view.getClass().toString());
        Log.d("ADAPTER", "PARENT "+parent.getClass().toString());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Marca marca = marcaList.get(position);
        holder.marca = marca;
        holder.tvMarca.setText(marca.getDescricao());
        holder.tvIdMarca.setText(String.valueOf(marca.getId()));
    }

    @Override
    public int getItemCount() {
        return marcaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {

        public TextView tvMarca;
        public TextView tvIdMarca;
        public Marca marca;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvMarca = (TextView) view.findViewById(R.id.tvMarca);
            tvIdMarca = (TextView) view.findViewById(R.id.tvIdMarca);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(view.getContext(), "Você clicou em "+marca.getId()+" - "+marca.getDescricao(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            Marca removed = marcaList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            return removed != null;
        }
    }
}
