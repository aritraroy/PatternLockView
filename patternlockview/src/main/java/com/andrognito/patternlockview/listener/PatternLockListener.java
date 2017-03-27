package com.andrognito.patternlockview.listener;

/**
 * Created by aritraroy on 19/03/17.
 */

import com.andrognito.patternlockview.PatternLockView;

import java.util.List;

/**
 * The callback interface for detecting patterns entered by the user
 */
public interface PatternLockListener {

    /**
     * Fired when the pattern drawing has just started
     */
    void onStarted();

    /**
     * Fired when the pattern has been drawn to
     * one more {@link com.andrognito.patternlockview.PatternLockView.Dot}
     */
    void onProgress(List<PatternLockView.Dot> progressPattern);

    /**
     * Fired when the user has completed drawing the pattern
     */
    void onComplete(List<PatternLockView.Dot> pattern);

    /**
     * Fired when the patten has been cleared
     */
    void onCleared();
}