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
public class HomePageTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(HomePage.class,true,false);

    @Test
    public void txtWelcome() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.txtWelcome)).check(matches(withText("Welcome to TIM.")));
    }

    @Test
    public void btnViewShops() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnViewShops)).check(matches(withText("VIEW SHOPS")));
    }

    @Test
    public void btnCreateShops() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnCreateItem)).check(matches(withText("CREATE SHOP")));
    }

    @Test
    public void btnViewOwnYes() throws Exception{
        ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);
        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("course", "a");
        rule.launchActivity(new Intent().putExtras(b));

        onView(withId(R.id.btnViewOwnShop)).check(matches(withText("VIEW OWN SHOP")));
        onView(withId(R.id.btnViewOwnShop)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void btnViewOwnNo() throws Exception{
        ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);
        Bundle b = new Bundle();
        b.putString("username", "noshop");
        b.putString("course", "1");
        rule.launchActivity(new Intent().putExtras(b));

        onView(withId(R.id.btnViewOwnShop)).check(matches(withText("VIEW OWN SHOP")));
        onView(withId(R.id.btnViewOwnShop)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }



    @Test
    public void logout() {
        ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("course", "a");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnLogOut));

        onView(withId(R.id.btnLogOut)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

        onView(withId(R.id.login_button)).check(matches(withText("LOGIN")));
        secondAct.finish();


    }

}
