package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.avaliacaobim2p1.Model.Usuario;

public class AvaliadorCadastroFragment extends Fragment {
    private EditText edNome,edLogin,edSenha ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_avaliador_cadastro, container, false);

        edNome = v.findViewById(R.id.editAvaliadorNome);
        edLogin = v.findViewById(R.id.editAvaliadorLogin);
        edSenha = v.findViewById(R.id.editAvaliadorSenha);
        Button btn = v.findViewById(R.id.btnCadastrarAvaliador);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Usuario novoUsuario = new Usuario(
                        edNome.getText().toString(),
                        edLogin.getText().toString(),
                        edSenha.getText().toString(),
                        "Avaliador"
                );

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase.getInstance(getContext()).appDao().cadastrarUsuario(novoUsuario);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Cadastro realizado!", Toast.LENGTH_SHORT).show();
                                ((MainActivity) getActivity()).substituirFragmento(new AvaliadorLoginFragment());
                            }
                        });
                    }
                }).start();
            }
        });
        return v;
    }
}