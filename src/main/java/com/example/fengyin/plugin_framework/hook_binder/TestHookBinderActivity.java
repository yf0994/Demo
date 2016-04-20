package com.example.fengyin.plugin_framework.hook_binder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.fengyin.plugin_framework.HookHelper;
import com.example.fengyin.plugin_framework.R;

public class TestHookBinderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_hook_binder);
        try {
            BinderHookHelper.hookClipboardService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText editText = new EditText(this);
        setContentView(editText);

        ClipboardManager manager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        if(manager.hasPrimaryClip()){
            ClipData data = manager.getPrimaryClip();
            ClipData.Item item = data.getItemAt(0);
            editText.setText(item.getText().toString());
        }
    }
}
