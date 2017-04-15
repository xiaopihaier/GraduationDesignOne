package com.example.baby.graduationdesignone;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username_input, password_input;
    Button login;
    StringBuilder stringBuilder;
    String line, msg, username, token;
    static final String LOGIN = "登陆成功";
    public final static String SER_KEY = "cn.caiwb.intent.ser";
    static final int UPDATE_TEXT = 1;
    static final int UPDATE_TEXT_2 = 2;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case UPDATE_TEXT:
                    Intent main = new Intent(Login.this, MainActivity.class);
                    startActivity(main);
                    Login.this.finish();
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE_TEXT_2:
                    Toast.makeText(Login.this, "错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        InitView();
    }


    private void InitView() {
        username_input = (EditText) findViewById(R.id.username_input);
        password_input = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        password_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            //回车键事件
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    password_input.setCursorVisible(false);
                    // 关闭软键盘
                    InputMethodManager imm_down = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 得到InputMethodManager的实例
                    if (imm_down.isActive()) {
                        // 如果开启
                        imm_down.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (TextUtils.isEmpty(username_input.getText().toString().trim())) {
                    Toast.makeText(this, "学号不可为空", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password_input.getText().toString().trim())) {
                    Toast.makeText(this, "密码不可为空", Toast.LENGTH_LONG).show();
                } else {
                    Get();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //                            if (LOGIN.equals(msg)) {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message);
                            //                            } else {
                            //                                Message message = new Message();
                            //                                message.what = UPDATE_TEXT_2;
                            //                                handler.sendMessage(message);
                            //                            }
                        }
                    }).start();
                }
        }
    }

    private void Get() {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    Json();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute("http://xiaopihaier.vicp.io/api/login?account=" + username_input.getText().toString().trim() + "&password=" + password_input.getText().toString().trim());
    }

    private void Json() {
        try {
            JSONObject jsonObject1 = new JSONObject(stringBuilder.toString());
            msg = jsonObject1.optString("msg");
            Log.i("msg", msg);
            JSONObject personObject = jsonObject1.getJSONObject("data");
            username = personObject.optString("userName");
            token = personObject.optString("token");
            Log.i("username", username);
            Log.i("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
