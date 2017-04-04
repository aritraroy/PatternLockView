package com.andrognito.rxpatternlockview.events;

import com.andrognito.patternlockview.PatternLockView;

import java.util.List;

/**
 * Created by aritraroy on 01/04/17.
 */

public class PatternLockCompleteEvent extends BasePatternLockEvent {

    public PatternLockCompleteEvent(List<PatternLockView.Dot> pattern) {
        super(pattern);
    }
}
