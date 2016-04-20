package com.example.fengyin.plugin_framework.view;

import android.app.Activity;
import android.content.Context;

import com.category.base.BaseView;

/**
 * Created by fengyin on 16-4-19.
 */
public interface ILoginView extends BaseView {

    public String getEmail();

    public String getPassword();

    public void clearErrorInfo();

    public void setEmailError(String msg);

    public void setPasswordError(String msg);

    public void emailViewRequestFocus();

    public void passwordViewRequestFocus();

    public String emailErrorFieldRequired();

    public String emailErrorInvalid();

    public String passwordErrorInvalid();

    public Activity getActivity();

}
