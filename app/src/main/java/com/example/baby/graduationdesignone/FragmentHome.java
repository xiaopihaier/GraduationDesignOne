package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by baby on 2017/4/14.
 */

public class FragmentHome extends Fragment implements View.OnClickListener {
    Button home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        IntentView(view);
        return view;
    }

    private void IntentView(View view) {
        home = (Button) view.findViewById(R.id.home);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                Log.i("FraHome", "home");
                break;
        }
    }
}
