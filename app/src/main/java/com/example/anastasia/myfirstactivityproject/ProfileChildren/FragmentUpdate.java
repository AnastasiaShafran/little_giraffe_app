package com.example.anastasia.myfirstactivityproject.ProfileChildren;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anastasia.myfirstactivityproject.R;

public class FragmentUpdate extends Fragment {
    Activity context ;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View myView = inflater.inflate(R.layout.activity_row_children,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
