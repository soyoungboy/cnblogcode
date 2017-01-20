package com.example.msi1.myapplication.intentservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.msi1.myapplication.R;

import java.util.List;

/**
 * IntentSerice的试例activity
 * Created by soyoungboy on 2017/1/20.
 */

public class IntentServiceDemoActivity extends FragmentActivity implements View.OnClickListener {

    private android.widget.Button btnfirst;
    private android.widget.Button btnsecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentservicedemo);
        btnsecond = (Button) findViewById(R.id.btn_second);
        btnfirst = (Button) findViewById(R.id.btn_first);
        btnfirst.setOnClickListener(this);
        btnsecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second:
                startMyIntentService();
                break;
            case R.id.btn_first:
                startMyIntentService();
                break;
            default:
                break;

        }
    }

    private void startMyIntentService() {
        Intent startServiceIntent = new Intent("com.soyoungboy.intentservice");
        Bundle bundle = new Bundle();
        bundle.putString("param", "oper1");
        startServiceIntent.putExtras(bundle);
        startService(createExplicitFromImplicitIntent(this,startServiceIntent));
    }

    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     *
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     *
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
