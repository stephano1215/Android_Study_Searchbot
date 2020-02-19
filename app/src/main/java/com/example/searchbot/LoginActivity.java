package com.example.searchbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.example.searchbot.databinding.ActivityLoginBinding;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    OAuthLogin mOAuthLoginModule = OAuthLogin.getInstance();
    private String AccessID = "gUVcqgOxgr2QaO8AZ1Af";
    private String AccessSecret = "ahgeg1Xgqh";

    public ObservableField<String> sampletitle = new ObservableField<>("");
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setModel(this);


        mOAuthLoginModule.init(
                LoginActivity.this
                ,AccessID
                ,AccessSecret
                ,"검색봇"
        );

        OAuthLoginButton mOAuthLoginButton = findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

        binding.buttonOAuthLoginImg.setOAuthLoginHandler(mOAuthLoginHandler);


    }

//    public void onClick() {
//        sampletitle.set(new Date().toString());
//    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                //싱글턴 : 앱 전체에 영향을 미칠 경우 사용 , 남발은 안좋다
                String token = mOAuthLoginModule.getAccessToken(LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                intent.putExtra("Token", token);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    };
}
