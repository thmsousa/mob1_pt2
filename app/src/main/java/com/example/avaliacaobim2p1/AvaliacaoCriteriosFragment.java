package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.avaliacaobim2p1.Model.Criterio;
import com.example.avaliacaobim2p1.Model.Relatorio;

import java.util.List;

public class AvaliacaoCriteriosFragment extends Fragment {
    private ListView listViewCriterios;
    private List<Criterio> listaCriteriosGlobal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_avaliacao_criterios, container, false);

        listViewCriterios = v.findViewById(R.id.listViewCriterios);
        Button btnFinalizar = v.findViewById(R.id.btnFinalizarAvaliacao);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaCriteriosGlobal == null || listaCriteriosGlobal.isEmpty()) {
                    Toast.makeText(getContext(), "Nenhum critério carregado", Toast.LENGTH_SHORT).show();
                    return;
                }

                double pontuacaoTotal = 0;
                for (int i = 0; i < listaCriteriosGlobal.size(); i++) {
                    pontuacaoTotal += listaCriteriosGlobal.get(i).notaAtribuida;
                }

                final double resultadoFinal = pontuacaoTotal;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Relatorio novoRelatorio = new Relatorio(resultadoFinal, "01/02/2026");
                        AppDataBase.getInstance(getContext()).appDao().inserirRelatorio(novoRelatorio);

                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    irParaTelaResultado(resultadoFinal);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        buscarCriteriosNoBanco();
        return v;
    }

    private void irParaTelaResultado(double pontuacao) {
        Bundle bundle = new Bundle();
        bundle.putDouble("nota_final", pontuacao);

        ResultadoAvaliacaoFragment fragmentoResultado = new ResultadoAvaliacaoFragment();
        fragmentoResultado.setArguments(bundle);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).substituirFragmento(fragmentoResultado);
        }
    }

    private void buscarCriteriosNoBanco() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Criterio> lista = AppDataBase.getInstance(getContext()).appDao().listarTodosCriterios();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listaCriteriosGlobal = lista;
                            MyAdapter adapter = new MyAdapter(getContext(), listaCriteriosGlobal);
                            listViewCriterios.setAdapter(adapter);
                        }
                    });
                }
            }
        }).start();
    }
}