package com.andrognito.rxpatternlockview.observables;

import com.andrognito.patternlockview.PatternLockView;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by aritraroy on 01/04/17.
 */

public abstract class BasePatternLockViewObservable<BasePatternLockEvent>
        extends Observable<BasePatternLockEvent> {
    protected PatternLockView mPatternLockView;

    protected BasePatternLockViewObservable(PatternLockView patternLockView) {
        mPatternLockView = patternLockView;
    }

    protected abstract void subscribeListener(Observer<? super BasePatternLockEvent> observer);
}
