![PatternLockView](https://github.com/aritraroy/PatternLockView/blob/master/screenshots/pattern-lock-view-banner.png?raw=true)

# PatternLockView
An easy-to-use, customizable, Material Design ready Pattern Lock view for Android.

### Specs
[ ![Download](https://api.bintray.com/packages/aritraroy/maven/patternlockview/images/download.svg) ](https://bintray.com/aritraroy/maven/patternlockview/_latestVersion) [![API](https://img.shields.io/badge/API-14%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=14) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PatternLockView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5515)


This library allows you to implement pattern locking mechanism in your app **easily and quickly**. It is very easy to use and there are **plenty of customization options** available to change the functionality and look-and-feel of this view to match your needs.

It also **supports RxJava 2 view bindings**, so if you are a fan of reactive programming (just like me), you can get a stream of updates as the user draws the pattern.

![PatternLockView](https://github.com/aritraroy/PatternLockView/blob/master/screenshots/pattern_lock_view_small.gif?raw=true) ![PatternLockView](https://github.com/aritraroy/PatternLockView/blob/master/screenshots/pattern_lock_view_2_small.gif?raw=true)


# Download

This library is available in **jCenter** which is the default Maven repository used in Android Studio.

## Gradle 
```gradle
dependencies {
    // other dependencies here
    
    compile 'com.andrognito.patternlockview:patternlockview:1.0.0'
    // Optional, for RxJava2 adapter
    compile 'com.andrognito.patternlockview:patternlockview-reactive:1.0.0'
}
```

### Spread Some :heart:
[![GitHub stars](https://img.shields.io/github/stars/aritraroy/PatternLockView.svg?style=social&label=Star)](https://github.com/aritraroy) [![GitHub followers](https://img.shields.io/github/followers/aritraroy.svg?style=social&label=Follow)](https://github.com/aritraroy)  
[![Twitter Follow](https://img.shields.io/twitter/follow/aritraroy93.svg?style=social)](https://twitter.com/aritraroy93) 


# Usage
We recommend you to check the [sample project](https://github.com/aritraroy/PatternLockView/blob/master/app/src/main/java/com/andrognito/patternlockdemo/MainActivity.java) to get a complete understanding of the library. The step-by-step implementation guide is as follows.

### Step 1

Place the view in your XML layout file.

```xml
    <com.andrognito.patternlockview.PatternLockView
        android:id="@+id/pattern_lock_view"
        android:layout_width="280dp"
        android:layout_height="280dp"/>
```

This is enough to get the view rendered in your layout. But you would certainly want to add a callback listener to listen to pattern changes.

### Step 2

Reference the view in code and add a listener to it.

```java
mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
mPatternLockView.addPatternLockListener(mPatternLockViewListener);
```

Implement the listener interface as follows,

```java
private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };
```

And that's it! Your PatternLockView is ready to rock. You might also want to remove the listeners when not needed,         `removePatternLockListener(mPatternLockViewListener);`


### Step 3 (Optional: ReactiveX Interface)

For the RxJava fanboys, this library supports RxJava 2 view bindings. You can subscribe to this view to get a stream of pattern change updates.

```java
RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing started");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progress: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern complete: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been cleared");
                        }
                    }
                });
```

If you are not interested in getting the compound event, you should subscribe to `patternComplete()` and/or `patternProgress()` for the specific updates. Have a detailed look [here](https://github.com/aritraroy/PatternLockView/blob/master/patternlockview-rxadapter/src/main/java/com/andrognito/rxpatternlockview/RxPatternLockView.java).

# Customization

There are several customization options available which you can use to completely change the look-and-feel and functionality of this view to match your needs.

### XML (Quick and Easy)

You can add various attributes to the PatternLockView from your XML layout.

```xml
  app:dotCount="3"                                        // Change the no.of dots in a row (or column)
  app:dotNormalSize="12dp"                                // Change the size of the dots in normal state
  app:dotSelectedSize="24dp"                              // Change the size of the dots in selected state
  app:pathWidth="4dp"                                     // Change the width of the path
  app:aspectRatioEnabled="true"                           // Set if the view should respect custom aspect ratio
  app:aspectRatio="square"                                // Set between "square", "width_bias", "height_bias"
  app:normalStateColor="@color/white"                     // Set the color of the pattern view in normal state
  app:correctStateColor="@color/primary"                  // Set the color of the pattern view in correct state
  app:wrongStateColor="@color/pomegranate"                // Set the color of the pattern view in error state     
  app:dotAnimationDuration="200"                          // Change the duration of the animating dots
  app:pathEndAnimationDuration="100"                      // Change the duration of the path end animaiton
```

### JAVA (Programatically)

You can also programatically change the properties of the view, thereby having more control over it.

```java
mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);       // Set the current viee more 
mPatternLockView.setInStealthMode(true);                                     // Set the pattern in stealth mode (pattern drawing is hidden)
mPatternLockView.setTactileFeedbackEnabled(true);                            // Enables vibration feedback when the pattern is drawn
mPatternLockView.setInputEnabled(false);                                     // Disables any input from the pattern lock view completely

mPatternLockView.setDotCount(3);
mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
mPatternLockView.setAspectRatioEnabled(true);
mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS); 
mPatternLockView.setNormalStateColor(ResourceUtils.getColor(this, R.color.white));
mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.primary));
mPatternLockView.setWrongStateColor(ResourceUtils.getColor(this, R.color.pomegranate));
mPatternLockView.setDotAnimationDuration(150);
mPatternLockView.setPathEndAnimationDuration(100);

```

# Contribution

This library is inspired from AOSP's [LockPatternView](https://github.com/android/platform_frameworks_base/blob/master/core/java/com/android/internal/widget/LockPatternView.java). There are lots of improvements and customization options added so that you can get started without any hassle. If you find a bug or would like to improve any aspect of it, feel free to contribute with pull requests.


# About The Author

### Aritra Roy

Android & Backend Developer. Blogger. Designer. Fitness Enthusiast.

<a href="https://play.google.com/store/apps/details?id=com.codexapps.andrognito&hl=en"><img src="https://github.com/aritraroy/social-icons/blob/master/play-store-icon.png?raw=true" width="60"></a> <a href="https://blog.aritraroy.in/"><img src="https://github.com/aritraroy/social-icons/blob/master/medium-icon.png?raw=true" width="60"></a>
<a href="http://stackoverflow.com/users/2858654/aritra-roy"><img src="https://github.com/aritraroy/social-icons/blob/master/stackoverflow-icon.png?raw=true" width="60"></a>
<a href="https://twitter.com/aritraroy93"><img src="https://github.com/aritraroy/social-icons/blob/master/twitter-icon.png?raw=true" width="60"></a>


# License

```
Copyright 2017 aritraroy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
