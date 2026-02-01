package com.example.avaliacaobim2p1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button btnSup = view.findViewById(R.id.btnMenuSupervisor);
        btnSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).substituirFragmento(new SupervisorLoginFragment());
            }
        });

        Button btnAva = view.findViewById(R.id.btnMenuAvaliador);
        btnAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).substituirFragmento(new AvaliadorLoginFragment());
            }
        });

        return view;
    }
}