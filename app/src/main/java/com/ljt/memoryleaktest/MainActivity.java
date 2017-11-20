package com.ljt.memoryleaktest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements TestManager.OnDataArrivedListener {

    public static String TAG= MainActivity.class.getSimpleName();
    private static Context sContext;
    private static View sView;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第一种
//        sContext=this;
        //第二种
//        sView=new View(this);
        //第三种 注册，忘了解注册
        TestManager.getInstance().registerListener(this);
        //第四种 动画无限循环
        mButton = (Button) findViewById(R.id.btn_second);
        ObjectAnimator animator=ObjectAnimator.ofFloat(mButton,"rotation",0,360).setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
         Log.d(TAG,TAG+" ----->>>onPause() ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         Log.d(TAG,TAG+" ----->>>onDestroy ");
        //忘了这一步，也会造成泄漏
        //animator.cancel();
    }

    @Override
    public void onDataArrived(Object data) {

    }
}
