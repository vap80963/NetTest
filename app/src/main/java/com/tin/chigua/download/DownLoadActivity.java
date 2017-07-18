package com.tin.chigua.download;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tin.chigua.tcpsocket.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hasee on 7/4/2017.
 */

public class DownLoadActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.start_download_btn)
    Button mStartDownloadBtn;
    @BindView(R.id.stop_download_btn)
    Button mStopDownloadBtn;
    @BindView(R.id.input_download_link)
    EditText mInputDownloadLink;
    @BindView(R.id.download_size)
    TextView mDownloadSize;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mProgressBar.setProgress(msg.getData().getInt("size"));
                    float num = mProgressBar.getProgress() / mProgressBar.getMax();
                    int result = (int) (num * 100);
                    mDownloadSize.setText("已下载：" + result + "%");
                    if(mProgressBar.getProgress() == mProgressBar.getMax()){
                        Toast.makeText(DownLoadActivity.this,"已下载完成！",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case -1:
                    Toast.makeText(DownLoadActivity.this,"下载出错了！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.start_download_btn, R.id.stop_download_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_download_btn:

                break;
            case R.id.stop_download_btn:
                break;
            default:
                break;
        }
    }

}
