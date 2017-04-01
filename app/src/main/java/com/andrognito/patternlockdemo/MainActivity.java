package com.andrognito.patternlockdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.andrognito.rxpatternlockview.RxPatternLockView;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private PatternLockView mPatternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mPatternLockView = (PatternLockView) findViewById(R.id.patter_lock_view);
        mPatternLockView.setDotCount(4);
        mPatternLockView.setAspectRatioEnabled(true);
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setNormalStateColor(ResourceUtils.getColor(this, R.color.colorPrimary));
        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.colorAccent));

        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent patternLockEvent) throws Exception {
                        Log.d(this.getClass().getName(), "Event Type " + patternLockEvent.getEventType());
                        Log.d(this.getClass().getName(), "Pattern " + patternLockEvent.getPattern().toString());
                    }
                });
    }
}
