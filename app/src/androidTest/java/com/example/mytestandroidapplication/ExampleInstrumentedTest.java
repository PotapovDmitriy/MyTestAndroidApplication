package com.example.mytestandroidapplication;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.core.StringContains.containsString;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<NavMainActivity> activityActivityTestRule = new ActivityTestRule<>(NavMainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.mytestandroidapplication", appContext.getPackageName());
    }

    @Test
    public void checkEditCity() {
        onView(withId(R.id.etCity)).perform(typeText("Korkino"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.show_weather)).perform(click());
        onView(withId(R.id.temp)).check(matches(isDisplayed()));
    }

    @Test
    public void checkListOfWeather() {
        onView(withId(R.id.etCity)).perform(typeText("Korkino"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.show_weather)).perform(click());

        onView(Matchers.anyOf(
                withContentDescription("Открыть панель навигации"),
                withContentDescription("Open navigation drawer")))
                .perform(click());
        onView(withText("Список запросов")).perform(click());
        onView(withId(R.id.content)).check(matches(withText(containsString("Korkino"))));
    }

}
