package com.example.espresso_tutorial;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void stubIntentTest() {
        // Stub intent
        Intent intent = new Intent();
        intent.setData(Uri.parse("content://com.android.contacts/data/1"));
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
        intending(toPackage("com.android.contacts")).respondWith(result);

        // find the button and perform click action
        onView(withId(R.id.call_contact_button)).perform(click());

        // get context
        Context targetContext2 = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // get phone number
        String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
        Cursor cursor =
                targetContext2.getContentResolver().query(Uri.parse("content://com.android.contacts/data/1"), projection, null, null, null);

        cursor.moveToFirst();
        int numberColumnIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String number = cursor.getString(numberColumnIndex);

        // now, check the data
        onView(withId(R.id.edit_text_phone_number))
                .check(matches(withText(number)));
    }

    @Test
    public void validateIntentTest() {
        onView(withId(R.id.edit_text_phone_number))
                .perform(typeText(PHONE_NUMBER), closeSoftKeyboard());
        onView(withId(R.id.button)) .perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData("tel:" + PHONE_NUMBER),
                toPackage(DIALER_PACKAGE_NAME)));
    }

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);
    private static final String PHONE_NUMBER = "1 234-567-890";
    private static final String DIALER_PACKAGE_NAME = "com.google.android.dialer";


}