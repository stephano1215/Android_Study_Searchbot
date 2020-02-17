package com.example.searchbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    OAuthLogin mOAuthLoginModule = OAuthLogin.getInstance();
    private String AccessID = "gUVcqgOxgr2QaO8AZ1Af";
    private String AccessSecret = "ahgeg1Xgqh";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mOAuthLoginModule.init(
                LoginActivity.this
                ,AccessID
                ,AccessSecret
                ,"검색봇"
        );

        OAuthLoginButton mOAuthLoginButton = findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
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
