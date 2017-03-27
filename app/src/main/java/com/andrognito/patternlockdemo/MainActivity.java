package com.andrognito.patternlockdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.rxpatternlockview.PatternLockEvent;
import com.andrognito.rxpatternlockview.RxPatternLockView;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private PatternLockView mPatternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatternLockView = (PatternLockView) findViewById(R.id.patternLockView);
        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockEvent>() {
                    @Override
                    public void accept(PatternLockEvent patternLockEvent) throws Exception {
                        Log.d(this.getClass().getName(), "Event Type " + patternLockEvent.getEventType());
                        Log.d(this.getClass().getName(), "Pattern " + patternLockEvent.getPattern().toString());
                    }
                });
    }
}
