package com.tin.chigua.video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tin.chigua.tcpsocket.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hasee on 7/11/2017.
 */

public class PlayVideoActivity extends AppCompatActivity {

    @BindView(R.id.activity_video_play_surfv)
    SurfaceView mSurfaceView;
    @BindView(R.id.activity_video_play_play)
    Button mPlayBtn;
    @BindView(R.id.activity_video_play_replay)
    Button mReplayBtn;
    @BindView(R.id.activity_video_play_screenShot)
    Button mScreenShotBtn;
    @BindView(R.id.activity_video_play_changeVedioSize)
    Button mChangeVedioSizeBtn;
    @BindView(R.id.activity_video_play_showTime)
    TextView mPlayTimeTv;
    @BindView(R.id.activity_video_play_seekbar)
    SeekBar mSeekbar;
    @BindView(R.id.activity_video_play_progressbar)
    ProgressBar mProgressbar;

    private SurfaceHolder mSurfaceHolder;
    private MyHandler myHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        init();

    }

    private static void playVideo() {

    }

    private void init() {
        //获取SurfaceView的实例后，一定要通过getHolder来获取SurfaceHolder。SurfaceHolder是SurfaceView的控制
        mSurfaceHolder = mSurfaceView.getHolder();
        //设置为SurfaceView不管理自己的缓存区，这个方法虽然提示为过时，但还是要设置，避免视频播放时，出现有声音没图像的问题
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //一定要对SurfaceHolder添加回调实现addCallback
        mSurfaceHolder.addCallback(new SurfaceCallback());
    }

    @OnClick({R.id.activity_video_play_play, R.id.activity_video_play_replay, R.id.activity_video_play_screenShot,
            R.id.activity_video_play_changeVedioSize, R.id.activity_video_play_seekbar})
    public void OnClick(View v) {


    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Message message = new Message();
            message.what = 1;
            myHandler.sendEmptyMessage(message.what);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }


    //Handler静态内部类
    private static class MyHandler extends Handler {
        //弱引用   和Handler静态内部类两者相结合，避免内存泄漏
        WeakReference<PlayVideoActivity> weakReference;

        public MyHandler(PlayVideoActivity activity) {
            weakReference = new WeakReference<PlayVideoActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    playVideo();
                    break;
                default:
                    break;
            }
        }
    }

}
