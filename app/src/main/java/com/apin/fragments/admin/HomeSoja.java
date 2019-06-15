package com.apin.fragments.admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apin.EditsAdmin.EditBranchy;
import com.apin.EditsAdmin.EditGlycines;
import com.apin.EditsAdmin.EditIncognita;
import com.apin.EditsAdmin.EditJavanica;
import com.apin.EditsAdmin.EditZeae;
import com.apin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeSoja#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeSoja extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_soja, container, false);

        Button glycines = (Button) view.findViewById(R.id.glycines);
        Button icognita = (Button) view.findViewById(R.id.icognita);
        Button javanica = (Button) view.findViewById(R.id.javanica);
        glycines.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditGlycines.class);
                startActivity(intent);
            }
        });
        icognita.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditIncognita.class);
                startActivity(intent);
            }
        });
        javanica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditJavanica.class);
                startActivity(intent);
            }
        });

        return  view;
    }
    public static HomeSoja newInstance() {
        return new HomeSoja();
    }
}
