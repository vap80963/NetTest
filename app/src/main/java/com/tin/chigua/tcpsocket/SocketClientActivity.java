package com.tin.chigua.tcpsocket;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SocketClientActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.et_receive)
    EditText et_receive;
    @BindView(R.id.bt_send)
    Button bt_send;
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;  //用于客户端和服务端的输入


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent service = new Intent(this, TCPService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                connectSocketServer();
            }
        }.start();
    }

    @OnClick(R.id.bt_send)
    public void onClick(View v){
        if (v.getId() == R.id.bt_send){
            final String msg = et_receive.getText().toString();
            //向服务器发送信息
            if (!TextUtils.isEmpty(msg) && null != mPrintWriter) {
                mPrintWriter.println(msg);
                //获取TextView原有的文字，换行，添加新数据
                tv_message.setText(tv_message.getText() + "\n" + "客户端：" + msg);
                et_receive.setText("");
            }
        }
    }

    private void connectSocketServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                //选择和服务器相同的端口8688
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            } catch (IOException e) {
                SystemClock.sleep(1000);
            }
        }
        try {
            // 接收服务器端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!isFinishing()) {
                final String msg = br.readLine();
                if (msg != null) {
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          tv_message.setText(tv_message.getText() + "\n" + "服务端：" + msg);
                                      }
                                  }
                    );
                }
            }
            mPrintWriter.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
