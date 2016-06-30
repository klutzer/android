package com.example.erico.geotests;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static org.hamcrest.Matchers.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
public class ApplicationTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sayHello(){
        onView(withText("Clique aqui!")).perform(click());
        onView(withText("Clicado!"))
                .inRoot(withDecorView(is(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        //onView(withId(R.id.textView)).check(matches(withText("Hello, World!")));
    }
}