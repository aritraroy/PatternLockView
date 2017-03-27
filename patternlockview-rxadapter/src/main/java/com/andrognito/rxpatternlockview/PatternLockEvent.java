package com.andrognito.rxpatternlockview;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.andrognito.patternlockview.PatternLockView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.andrognito.rxpatternlockview.PatternLockEvent.EventType.PATTERN_CLEARED;
import static com.andrognito.rxpatternlockview.PatternLockEvent.EventType.PATTERN_COMPLETE;
import static com.andrognito.rxpatternlockview.PatternLockEvent.EventType.PATTERN_PROGRESS;
import static com.andrognito.rxpatternlockview.PatternLockEvent.EventType.PATTERN_STARTED;

/**
 * Created by aritraroy on 27/03/17.
 */

public final class PatternLockEvent {

    @IntDef({PATTERN_STARTED, PATTERN_PROGRESS, PATTERN_COMPLETE, PATTERN_CLEARED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
        int PATTERN_STARTED = 0;
        int PATTERN_PROGRESS = 1;
        int PATTERN_COMPLETE = 2;
        int PATTERN_CLEARED = 3;
    }

    private final int mEventType;
    private final List<PatternLockView.Dot> mPattern;

    public PatternLockEvent(@EventType int eventType, List<PatternLockView.Dot> pattern) {
        mEventType = eventType;
        mPattern = pattern;
    }

    @EventType
    public int getEventType() {
        return mEventType;
    }

    @Nullable
    public List<PatternLockView.Dot> getPattern() {
        if (mPattern == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(mPattern);
    }
}
