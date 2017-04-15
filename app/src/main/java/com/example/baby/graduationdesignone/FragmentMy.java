package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.baby.graduationdesignone.R.id.btn_pick_photo;
import static com.example.baby.graduationdesignone.R.id.btn_take_photo;

/**
 * Created by baby on 2017/4/14.
 */

public class FragmentMy extends Fragment implements View.OnClickListener {
    ImageView cirImageView;//头像
    LinearLayout about;
    LinearLayout Cancellation;//注销
    //自定义的弹出框类
    PopupMenu menuWindow;

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
                //实例化SelectPicPopupWindow
                menuWindow = new PopupMenu(getActivity(), itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(getActivity().findViewById(R.id.my), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
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

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case btn_take_photo:
                    btn_take_photo();
                    Toast.makeText(getActivity(), "拍照", Toast.LENGTH_SHORT).show();
                    break;
                case btn_pick_photo:
                    btn_pick_photo();
                    Toast.makeText(getActivity(), "相册", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }


        }

    };

    private void btn_take_photo() {

    }

    private void btn_pick_photo() {

    }
}
