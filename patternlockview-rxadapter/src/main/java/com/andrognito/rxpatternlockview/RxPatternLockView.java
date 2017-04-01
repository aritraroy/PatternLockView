package com.andrognito.rxpatternlockview;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.andrognito.rxpatternlockview.events.PatternLockProgressEvent;
import com.andrognito.rxpatternlockview.observables.PatternLockViewCompleteObservable;
import com.andrognito.rxpatternlockview.observables.PatternLockViewCompoundObservable;
import com.andrognito.rxpatternlockview.observables.PatternLockViewProgressObservable;

import io.reactivex.Observable;

/**
 * Created by aritraroy on 27/03/17.
 */

public class RxPatternLockView {

    /**
     * Create an observable for all events of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     */
    public static Observable<PatternLockCompoundEvent> patternChanges(PatternLockView patternLockView) {
        if (patternLockView == null) {
            throw new IllegalArgumentException("PatternLockView can not be null.");
        }
        return new PatternLockViewCompoundObservable(patternLockView);
    }

    /**
     * Create an observable for only the pattern complete event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     */
    public static Observable<PatternLockCompleteEvent> patternComplete(PatternLockView patternLockView) {
        if (patternLockView == null) {
            throw new IllegalArgumentException("PatternLockView can not be null.");
        }
        return new PatternLockViewCompleteObservable(patternLockView);
    }

    /**
     * Create an observable for only the pattern progress event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     */
    public static Observable<PatternLockProgressEvent> patternProgress(PatternLockView patternLockView) {
        if (patternLockView == null) {
            throw new IllegalArgumentException("PatternLockView can not be null.");
        }
        return new PatternLockViewProgressObservable(patternLockView);
    }
}
