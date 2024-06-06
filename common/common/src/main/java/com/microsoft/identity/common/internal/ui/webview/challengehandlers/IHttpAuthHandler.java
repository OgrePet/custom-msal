package com.microsoft.identity.common.internal.ui.webview.challengehandlers;

public interface IHttpAuthHandler {
    void proceed(String username, String password);

    void cancel();
}
