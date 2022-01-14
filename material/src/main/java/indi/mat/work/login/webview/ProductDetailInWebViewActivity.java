package indi.mat.work.login.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import indi.mat.work.login.R;

public class ProductDetailInWebViewActivity extends AppCompatActivity {

    ActivityProductDetailsBinding binding;

    String url = "http://10.16.78.61:8699/index.html?prouctId=PRODUCT&token=TOKEN";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWebView();
        String productId = getIntent().getStringExtra("productId");
        url=url.replace("PRODUCT",productId);
        if(MaterialApplication.getUser()!=null){
            url=url.replace("TOKEN", MaterialApplication.getUser().getToken()+" ");
        }
        binding.webview.loadUrl(url);
        initEvent();
    }

    private void initEvent() {
        binding.imageView4.setOnClickListener(v -> onBackPressed());
    }

    private void initWebView() {
                binding.webview.getSettings().setJavaScriptEnabled(true);
                binding.webview.getSettings().setDomStorageEnabled(true);
                binding.webview.getSettings().setLoadWithOverviewMode(true);
                binding.webview.getSettings().setUseWideViewPort(true);
                binding.webview.getSettings().setTextZoom(100);
                binding.webview.setWebChromeClient(new WebChromeClient());
                binding.webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                Uri uri = request.getUrl();
                String url = uri.toString();
                if (url.contains("chatId")) {
                    String[] strings = url.split("=");
                    if (strings.length == 2) {
                        String chatId = strings[1];
                        MessageListActivity.start(ProductDetailsActivity.this, chatId);
                        return true;
                    }
                }
                Log.e("productDetail", uri.toString());

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }
        });
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
