package com.tin.chigua;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tin.chigua.download.DownLoadActivity;
import com.tin.chigua.service.TestActivity;
import com.tin.chigua.tcpsocket.R;
import com.tin.chigua.tcpsocket.SocketClientActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hasee on 7/4/2017.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ac_main_start_tcp_btn)
    Button mAcMainStartTcpBtn;
    @BindView(R.id.ac_main_start_download_btn)
    Button mAcMainStartDownloadBtn;
    @BindView(R.id.ac_main_start_service_btn)
    Button mAcMainStartServiceBtn;
    @BindView(R.id.ac_main_start_video_play_btn)
    Button mAcMainStartVideoPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.ac_main_start_download_btn, R.id.ac_main_start_tcp_btn,
            R.id.ac_main_start_service_btn,R.id.ac_main_start_video_play_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_main_start_tcp_btn:
                startActivity(new Intent(MainActivity.this, SocketClientActivity.class));
                break;
            case R.id.ac_main_start_download_btn:
                startActivity(new Intent(MainActivity.this, DownLoadActivity.class));
                break;
            case R.id.ac_main_start_service_btn:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            case R.id.ac_main_start_video_play_btn:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
