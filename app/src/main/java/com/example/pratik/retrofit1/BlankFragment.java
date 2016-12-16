package com.example.pratik.retrofit1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class BlankFragment extends Fragment {

    @InjectView(R.id.tv_country)
    TextView tvCountry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String tv_country1 = getArguments().getString("s2");


        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.inject(this, view);
        tvCountry.setText(tv_country1);
        return view;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
