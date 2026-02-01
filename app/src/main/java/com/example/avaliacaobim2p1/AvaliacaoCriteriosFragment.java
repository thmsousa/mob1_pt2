package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import com.example.avaliacaobim2p1.Model.Criterio;

import java.util.List;

public class AvaliacaoCriteriosFragment extends Fragment {
    private ListView listViewCriterios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_avaliacao_criterios, container, false);

        listViewCriterios = v.findViewById(R.id.listViewCriterios);
        buscarCriteriosNoBanco();
        return v;
    }

    private void buscarCriteriosNoBanco() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Criterio> lista = AppDataBase.getInstance(getContext()).appDao().listarTodosCriterios();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyAdapter adapter = new MyAdapter(getContext(), lista);
                        listViewCriterios.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}