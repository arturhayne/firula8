package br.com.htech.firula8.Login.LoginFragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.htech.firula8.R;
import br.com.htech.firula8.util.ApiConstants;

/**
 * Created by artur.oliveira on 30/01/2018.
 */

public class LoginFragment  extends Fragment implements LoginFragmentContract.View{
    private static String TAG = "LoginFragment";


    private WebView webView;
    private ProgressBar progressBar;

    android.webkit.CookieManager cookieManager;

    private LoginFragmentContract.UserAction userAction;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_login, container, false);

        userAction = new LoginFragmentPresenter(getActivity(),this);
        webView = v.findViewById(R.id.web_view);
        progressBar = v.findViewById(R.id.progress_bar);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //clear WebView
        clearWebView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            webView.setWebViewClient(new WebViewClient() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    Uri uri = request.getUrl();

                    if (ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_SCHEMA.equals(uri.getScheme())
                            && ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_HOST.equals(
                            uri.getHost())) {
                        String code = uri.getQueryParameter("code");
                        String error = uri.getQueryParameter("error");
                        if (!TextUtils.isEmpty(code)) {
                            loginPresenter.getAccessToken(code);
                        } else if (!TextUtils.isEmpty(error)) {
                            Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, request);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Uri uri = Uri.parse(url);
                    if (ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_SCHEMA.equals(uri.getScheme())
                            && ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_HOST.equals(
                            uri.getHost())) {
                        String code = uri.getQueryParameter(ApiConstants.CODE);
                        String error = uri.getQueryParameter(ApiConstants.ERROR_CODE);
                        if (!TextUtils.isEmpty(code)) {
                            loginPresenter.getAccessToken(code);
                        } else if (!TextUtils.isEmpty(error)) {
                            Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webView.loadUrl(ApiConstants.DRIBBBLE_AUTHORIZE_URL
                + "?client_id=" + ApiConstants.DRIBBBLE_CLIENT_ID
                + "&redirect_uri=" + ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI
                + "&scope=" + ApiConstants.DRIBBBLE_AUTHORIZE_SCOPE);
    }

    @Override
    public void setLoginPresenter(LoginPresenterImpl loginPresenter) {
        this.loginPresenter = loginPresenter;
    }


    @Override
    public void clearWebView() {
        cookieManager = CookieManager.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                // a callback which is executed when the cookies have been removed
                @Override
                public void onReceiveValue(Boolean aBoolean) {
                    Log.d(TAG, "Cookie removed: " + aBoolean);
                }
            });
        } else cookieManager.removeAllCookie();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginPresenter.detachFragmentView();
    }


}
