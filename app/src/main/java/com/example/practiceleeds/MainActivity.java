package com.example.practiceleeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.practiceleeds.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String hyper="https://";
    private String backStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebSettings webSettings= binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new CallBack());

        binding.webGoBtn.setOnClickListener(v ->{

            binding.webView.loadUrl(hyper+binding.webedt.getText().toString());
            backStore=(hyper+binding.webedt.getText().toString());

        });

        binding.webforBtn.setOnClickListener(v ->{
            binding.webView.goForward();
        });
        binding.webBackbtn.setOnClickListener(v ->{
            binding.webView.goBack();
        });

    }
    private class CallBack extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction()==KeyEvent.ACTION_DOWN){

            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    binding.webBackbtn.setOnClickListener(v->{
                        if (binding.webView.canGoBack()) {
                            binding.webView.goBack();
                        } else {
                            finish();
                        }
                    });
                    return true;
            }

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(event.getAction()==KeyEvent.ACTION_UP){
            switch (keyCode){

                case KeyEvent.KEYCODE_FORWARD:
                    binding.webforBtn.setOnClickListener(v ->{
                        if (binding.webView.canGoForward()){
                            binding.webView.goForward();
                        }
                        else {
                            finish();
                        }

                    });
                    return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}