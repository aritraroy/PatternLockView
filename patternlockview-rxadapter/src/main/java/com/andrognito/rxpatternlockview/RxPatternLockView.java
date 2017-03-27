package com.andrognito.rxpatternlockview;

import com.andrognito.patternlockview.PatternLockView;

import io.reactivex.Observable;

/**
 * Created by aritraroy on 27/03/17.
 */

public class RxPatternLockView {

    public static Observable<PatternLockEvent> patternChanges(PatternLockView patternLockView) {
        if (patternLockView == null) {
            throw new IllegalArgumentException("PatternLockView can not be null.");
        }
        return new PatternLockViewObservable(patternLockView);
    }
}
