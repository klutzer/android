package erico.fipeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import erico.fipeapp.business.Marca;
import erico.fipeapp.service.FipeService;
import erico.fipeapp.service.ServiceCallback;

public class MarcaFragment extends Fragment {

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarcaFragment() {}

    public static MarcaFragment newInstance() {
        MarcaFragment fragment = new MarcaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marca, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvMarcas);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recarregarMarcas();

        return view;
    }

    private void recarregarMarcas() {
        FipeService service = new FipeService();
        service.getMarcas(new ServiceCallback<List<Marca>>() {
            @Override
            public void onSuccess(List<Marca> obj) {
                MarcaAdapter adapter = (MarcaAdapter) recyclerView.getAdapter();
                if (adapter == null) {
                    recyclerView.setAdapter(new MarcaAdapter(obj));
                }else {
                    adapter.setData(obj);
                }
            }
        });
    }


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

}
