package com.example.tim2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class displayProductTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(displayProduct.class,true,false);

    @Test
    public void viewProductOpening() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("shopName", "Toys Were Us");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.questionScrollView)).perform(click());
    }



}
//Finished