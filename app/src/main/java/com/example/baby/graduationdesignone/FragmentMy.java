package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by baby on 2017/4/14.
 */

public class FragmentMy extends Fragment implements View.OnClickListener {
    ImageView cirImageView;//头像
    LinearLayout about;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        IntentView(view);
        return view;
    }

    private void IntentView(View v) {
        cirImageView = (ImageView) v.findViewById(R.id.cirImageView);
        cirImageView.setOnClickListener(this);
        about = (LinearLayout) v.findViewById(R.id.about);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cirImageView:
                Intent cirImageView = new Intent(getActivity(), Login.class);
                startActivity(cirImageView);
                Log.i("my", "my");
                break;
            case R.id.about:
                Intent about = new Intent(getActivity(), About.class);
                startActivity(about);
                break;
        }
    }
}
