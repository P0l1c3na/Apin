package com.apin.fragments.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.apin.EditsAdmin.EditBranchy;
import com.apin.EditsAdmin.EditZeae;
import com.apin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeMilho#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeMilho extends Fragment {
    private Button btn;
    private LinearLayout mLinear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_milho, container, false);

        Button brachyurus = (Button) view.findViewById(R.id.brachyurus);
        Button zeae = (Button) view.findViewById(R.id.zeae);
        brachyurus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditBranchy.class);
                startActivity(intent);
            }
        });
        zeae.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditZeae.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public static HomeMilho newInstance() {
        return new HomeMilho();
    }

}
