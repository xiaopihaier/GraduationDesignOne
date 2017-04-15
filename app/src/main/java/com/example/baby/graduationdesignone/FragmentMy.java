package com.example.baby.graduationdesignone;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import static android.app.Activity.RESULT_OK;
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
    Bitmap bitmap;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Uri imageUri;
    static final int UPDATE_TEXT = 1;
    private Sql dbHelper;

    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void handleMessage(Message message) {
            switch (message.what) {
                case UPDATE_TEXT:
                    try {
                        cirImageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        IntentView(view);
        dbHelper.getWritableDatabase();
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
                    //启动相机
                    btn_take_photo();
                    break;
                case btn_pick_photo:
                    //启动相册
                    btn_pick_photo();
                    break;
                default:
                    break;
            }


        }

    };

    private void btn_take_photo() {
        //创建file对象 用于存储拍照后的图片
        File outputImage = new File(Environment.getExternalStorageDirectory(), "cirImageView.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("crop", "true");
                    //裁剪框比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    //图片输出大小
                    intent.putExtra("outputX", 100);
                    intent.putExtra("outputY", 100);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
                break;
            default:
                break;
        }
    }

    private void btn_pick_photo() {

    }
}
