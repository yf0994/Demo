package com.example.fengyin.plugin_framework.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.category.base.net.IReponseListener;
import com.category.base.net.Request;
import com.category.base.net.RequestCallback;
import com.category.base.presenter.BasePresenterImpl;
import com.example.fengyin.plugin_framework.R;
import com.example.fengyin.plugin_framework.Util;
import com.example.fengyin.plugin_framework.model.ILoginInteractor;
import com.example.fengyin.plugin_framework.model.ILoginInteractorImpl;
import com.example.fengyin.plugin_framework.view.ILoginView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by fengyin on 16-4-19.
 */
public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {

    private ILoginView mLoginView;
    private ILoginInteractor mLoginInteractor;

    public LoginPresenterImpl(ILoginView view){
        super(view);
        mLoginView = view;
        mLoginInteractor = new ILoginInteractorImpl(mLoginView.getActivity());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {

    }


    @Override
    public void attemptLogin() {
        mLoginView.clearErrorInfo();
        String email = mLoginView.getEmail();
        String password = mLoginView.getPassword();
        boolean cancel = false;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mLoginView.setPasswordError(mLoginView.passwordErrorInvalid());
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mLoginView.setEmailError(mLoginView.emailErrorFieldRequired());
            cancel = true;
        } else if (!isEmailValid(email)) {
            mLoginView.setPasswordError(mLoginView.emailErrorInvalid());
            cancel = true;
        }

        if(!cancel){
            mLoginInteractor.login(email, password, new RequestCallback() {
                @Override
                public void beforeRequest() {
                    mLoginView.showProgress();
                }

                @Override
                public void requestError(String msg) {
                    mLoginView.hideProgress();
                }

                @Override
                public void requestSuccess(Object o) {
                    mLoginView.hideProgress();
                }

                @Override
                public void requestComplete() {
                    mLoginView.hideProgress();
                }
            });
        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
