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

public class FragmentFind extends Fragment implements View.OnClickListener {
    Button find;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find, container, false);
        IntentView(view);
        return view;
    }

    private void IntentView(View view) {
        find = (Button) view.findViewById(R.id.find);
        find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find:
                Log.i("find", "find");
                break;
        }
    }
}
