package com.example.fengyin.plugin_framework;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.category.base.activity.BaseActivity;
import com.category.base.net.IReponseListener;
import com.category.base.net.Request;
import com.example.fengyin.plugin_framework.presenter.LoginPresenter;
import com.example.fengyin.plugin_framework.presenter.LoginPresenterImpl;
import com.example.fengyin.plugin_framework.view.ILoginView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mBasePresenter = new LoginPresenterImpl(this);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void registerListener() {
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mBasePresenter.attemptLogin();
    }

    @Override
    public String getEmail() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void clearErrorInfo() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void setEmailError(String msg) {
        mEmailView.setError(msg);
    }

    @Override
    public void setPasswordError(String msg) {
        mPasswordView.setError(msg);
    }

    @Override
    public void emailViewRequestFocus() {
        mEmailView.requestFocus();
    }

    @Override
    public void passwordViewRequestFocus() {
        mPasswordView.requestFocus();
    }

    @Override
    public String emailErrorFieldRequired() {
        emailViewRequestFocus();
        return getString(R.string.error_field_required);
    }

    @Override
    public String emailErrorInvalid() {
        emailViewRequestFocus();
        return getString(R.string.error_invalid_email);
    }

    @Override
    public String passwordErrorInvalid() {
        passwordViewRequestFocus();
        return getString(R.string.error_invalid_password);
    }

    @Override
    public Activity getActivity() {
        return LoginActivity.this;
    }

    @Override
    public void showProgress() {
        super.showProgress();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressView.setVisibility(View.GONE);
            }
        });
    }
}

