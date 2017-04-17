package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by baby on 2017/4/14.
 */

public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    ViewPager viewPager;
    View view_1, view_2, view_3, view_4;
    ArrayList<View> arrayList;
    int posin;

    ListView classfy_lv;
    LinearLayout classfy_lin;
    Fragment_quanqiugou f_quanqiugou;
    Fragment_other f_other;

    FragmentManager fm;
    FragmentTransaction ft;

    Button send_1, send_2, send_3, send_4;


    android.os.Handler myhandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                viewPager.setCurrentItem(posin % 4);
                posin++;
                myhandler.sendEmptyMessageDelayed(1, 2500);
            }
        }
    };

    //创建适配器
    PagerAdapter pagerAdapter = new PagerAdapter() {
        //获取viewpager的页数
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(arrayList.get(position));
        }

        //创建
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(arrayList.get(position));
            return arrayList.get(position);
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        IntentView(view);
        //将布局文件加载成view视图
        view_1 = inflater.inflate(R.layout.bttom_table1, null);
        view_2 = inflater.inflate(R.layout.bttom_lable2, null);
        view_3 = inflater.inflate(R.layout.bttom_lable3, null);
        view_4 = inflater.inflate(R.layout.bttom_lable4, null);


        arrayList = new ArrayList();
        arrayList.add(view_1);
        arrayList.add(view_2);
        arrayList.add(view_3);
        arrayList.add(view_4);

        viewPager.setAdapter(pagerAdapter);

        myhandler.sendEmptyMessageAtTime(1, 2500);

        f_quanqiugou = new Fragment_quanqiugou();
        f_other = new Fragment_other();
        fm = getFragmentManager();
        initView(view);
        classfy_lv.setOnItemClickListener(this);
        return view;
    }

    private void initView(View view) {
        classfy_lv = (ListView) view.findViewById(R.id.classfy_lv);
        classfy_lin = (LinearLayout) view.findViewById(R.id.classfy_lin);
        ft = fm.beginTransaction();
        ft.replace(R.id.classfy_lin, f_quanqiugou);
        ft.commit();
        send_1 = (Button) view.findViewById(R.id.send_1);
        send_1.setOnClickListener(this);
        send_2 = (Button) view.findViewById(R.id.send_2);
        send_2.setOnClickListener(this);
        send_3 = (Button) view.findViewById(R.id.send_3);
        send_3.setOnClickListener(this);
        send_4 = (Button) view.findViewById(R.id.send_4);
        send_4.setOnClickListener(this);
    }

    private void IntentView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_tabs);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        classfy_lv.smoothScrollToPositionFromTop(position, 0);
        switch (position) {
            case 0:
                ft = fm.beginTransaction();
                ft.replace(R.id.classfy_lin, f_quanqiugou);
                ft.commit();
                break;

            default:
                ft = fm.beginTransaction();
                ft.replace(R.id.classfy_lin, f_other);
                ft.commit();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_1:
                Intent send_1 = new Intent(getActivity(), Work_send.class);
                startActivity(send_1);
                break;
            case R.id.send_2:
                Intent send_2 = new Intent(getActivity(), Work_send.class);
                startActivity(send_2);
                break;
            case R.id.send_3:
                Intent send_3 = new Intent(getActivity(), Work_send.class);
                startActivity(send_3);
                break;
            case R.id.send_4:
                Intent send_4 = new Intent(getActivity(), Work_send.class);
                startActivity(send_4);
                break;
        }
    }
}
