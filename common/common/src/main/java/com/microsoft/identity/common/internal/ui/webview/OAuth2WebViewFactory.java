package com.microsoft.identity.common.internal.ui.webview;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lombok.NonNull;

public class OAuth2WebViewFactory {
    private static OAuth2WebViewFactory sharedInstance;

    public static OAuth2WebViewFactory getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new OAuth2WebViewFactory();
        }
        return sharedInstance;
    }

    public static void overrideSharedInstance(OAuth2WebViewFactory instance) {
        sharedInstance = instance;
    }

    public @NonNull WebView createWebView(@NonNull Context context) {
        return new WebView(context);
    }

    public void setupWebView(@NonNull WebView webView, @NonNull WebViewClient client) {
        webView.setWebViewClient(client);
    }
}
