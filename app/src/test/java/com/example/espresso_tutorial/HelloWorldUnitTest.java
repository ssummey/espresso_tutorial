package com.example.espresso_tutorial;

import android.content.Context;
//import androidx.test.InstrumentationRegistry;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
     * Instrumented test, which will execute on an Android device.
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */
    @RunWith(AndroidJUnit4.class)
    public class HelloWorldUnitTest {
        @Rule
        public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
        @Test
        public void view_isCorrect() {
            onView(withText("Hello World!")).check(matches(isDisplayed()));
        }
        @Test
        public void useAppContext() {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getTargetContext();
            assertEquals("com.tutorialspoint.espressosamples.helloworldapp", appContext.getPackageName());
        }
    }
