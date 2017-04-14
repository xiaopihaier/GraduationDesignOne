package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rbtn_home, rbtn_my;
    FragmentManager fm;


    FragmentHome fragmentHome = new FragmentHome();
    Fragment fragment;
    FragmentTransaction ft;
    int witchFragment = 0;//0表示初始化“首页”，1表示初始化“我”
    UserActivity muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentView();
        onRadioGroupClickListener();
    }

    private void onRadioGroupClickListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //判断是否为空  为空则实例
                if (fm == null) {
                    fm = getFragmentManager();
                }
                switch (checkedId) {
                    //首页
                    case R.id.rbtn_home:
                        //实例
                        fragment = fragmentHome;
                        //开启事务
                        ft = fm.beginTransaction();
                        //替换view
                        ft.replace(R.id.fl_fragment, fragment);
                        //提交事务
                        ft.commit();
                        break;
                    //发现
                    case R.id.rbtn_find:
                        //实例
                        fragment = new FragmentFind();
                        //开启事务
                        ft = fm.beginTransaction();
                        //替换view
                        ft.replace(R.id.fl_fragment, fragment);
                        //提交事务
                        ft.commit();
                        break;
                    //我
                    case R.id.rbtn_my:
                        //实例
                        fragment = new FragmentMy();
                        //开启事务
                        ft = fm.beginTransaction();
                        //替换view
                        ft.replace(R.id.fl_fragment, fragment);
                        //提交事务
                        ft.commit();
                        break;
                    default:
                        fragment = fragmentHome;
                        ft = fm.beginTransaction();
                        ft.replace(R.id.fl_fragment, fragment);
                        ft.commit();
                        break;
                }
            }
        });
    }

    private void IntentView() {
        // 实例化控件
        radioGroup = (RadioGroup) findViewById(R.id.rbtn_group);
        rbtn_home = (RadioButton) findViewById(R.id.rbtn_home);
        rbtn_my = (RadioButton) findViewById(R.id.rbtn_my);

        // 获取封装的值
        // witchFragment = getIntent().getIntExtra("login", 0);

        Bundle bundle = getIntent().getExtras();

        // 由于已经来就是这个界面如果不判读则会报错导致无法运行，
        // 则必须先进行判断bundle
        // 然后在获取对象
        if (bundle != null) {
            muser = (UserActivity) bundle
                    .getSerializable(Login.SER_KEY);
            System.out.println("str-----" + muser.getName());
            witchFragment = muser.getA();
        }

        // 接收实例fragment
        // 初始化设置页面
        // 跳转值，判断初始化那一个fragment
        switch (witchFragment) {
            case 0:
                // 默认选中
                rbtn_home.setChecked(true);
                fragment = fragmentHome;
                // 获得getFragmentManager
                fm = getFragmentManager();
                // 开启事务
                ft = fm.beginTransaction();
                // ft.setCustomAnimations(R.anim.fragment_anim,
                // R.anim.fragment_anim);
                // 替换页面
                ft.replace(R.id.fl_fragment, fragment);
                // 提交事务
                ft.commit();
                break;
            case 1:
                rbtn_my.setChecked(true);
                fragment = new FragmentMy();
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fl_fragment, fragment);
                ft.commit();
                break;

            default:
                break;
        }
    }
    //--------------使用onKeyUp()干掉他--------------

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
