package com.andrognito.rxpatternlockview.observables;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.rxpatternlockview.events.PatternLockProgressEvent;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by aritraroy on 01/04/17.
 */

public class PatternLockViewProgressObservable extends
        BasePatternLockViewObservable<PatternLockProgressEvent> {

    public PatternLockViewProgressObservable(PatternLockView patternLockView, boolean emitInitialValue) {
        super(patternLockView, emitInitialValue);
    }

    @Override
    protected void subscribeListener(Observer<? super PatternLockProgressEvent> observer) {
        InternalListener internalListener = new InternalListener(mPatternLockView, observer);
        observer.onSubscribe(internalListener);
        mPatternLockView.addPatternLockListener(internalListener);
    }

    @Override
    protected void subscribeActual(Observer<? super PatternLockProgressEvent> observer) {
        subscribeListener(observer);
        if (mEmitInitialValue) {
            observer.onNext(new PatternLockProgressEvent(mPatternLockView.getPattern()));
        }
    }

    private static final class InternalListener extends MainThreadDisposable
            implements PatternLockViewListener {
        private final PatternLockView view;
        private final Observer<? super PatternLockProgressEvent> observer;

        InternalListener(PatternLockView view, Observer<? super PatternLockProgressEvent> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockProgressEvent(progressPattern));
            }
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {

        }

        @Override
        public void onCleared() {

        }

        @Override
        protected void onDispose() {
            view.removePatternLockListener(this);
        }
    }
}
