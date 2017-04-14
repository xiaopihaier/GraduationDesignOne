package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
    LinearLayout Cancellation;//注销

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        IntentView(view);
        return view;
    }

    private void IntentView(View v) {
        cirImageView = (ImageView) v.findViewById(R.id.cirImageView);
        cirImageView.setOnClickListener(this);
        Cancellation = (LinearLayout) v.findViewById(R.id.Cancellation);
        Cancellation.setOnClickListener(this);
        about = (LinearLayout) v.findViewById(R.id.about);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cirImageView:
                //启动底部弹出菜单
                break;
            case R.id.Cancellation:
                Intent Cancellation = new Intent(getActivity(), Login.class);
                startActivity(Cancellation);
                break;
            case R.id.about:
                Intent about = new Intent(getActivity(), About.class);
                startActivity(about);
                break;
        }
    }
}
