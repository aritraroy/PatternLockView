package com.andrognito.rxpatternlockview;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;


/**
 * Created by aritraroy on 27/03/17.
 */

public class PatternLockViewObservable extends Observable<PatternLockEvent> {
    private PatternLockView mPatternLockView;

    public PatternLockViewObservable(PatternLockView patternLockView) {
        mPatternLockView = patternLockView;
    }

    @Override
    protected void subscribeActual(Observer<? super PatternLockEvent> observer) {
        Listener listener = new Listener(mPatternLockView, observer);
        observer.onSubscribe(listener);
        mPatternLockView.addPatternLockListener(listener);
    }

    final static class Listener extends MainThreadDisposable implements PatternLockListener {
        private final PatternLockView view;
        private final Observer<? super PatternLockEvent> observer;

        Listener(PatternLockView view, Observer<? super PatternLockEvent> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onStarted() {
            if (!isDisposed()) {
                observer.onNext(new PatternLockEvent(PatternLockEvent.EventType.PATTERN_STARTED, null));
            }
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockEvent(PatternLockEvent.EventType.PATTERN_PROGRESS,
                        progressPattern));
            }
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockEvent(PatternLockEvent.EventType.PATTERN_COMPLETE,
                        pattern));
            }
        }

        @Override
        public void onCleared() {
            if (!isDisposed()) {
                observer.onNext(new PatternLockEvent(PatternLockEvent.EventType.PATTERN_CLEARED, null));
            }
        }

        @Override
        protected void onDispose() {
            view.removePatternLockListener(this);
        }
    }
}
