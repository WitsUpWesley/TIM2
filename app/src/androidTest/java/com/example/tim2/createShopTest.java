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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class createShopTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(createShop.class,true,false);

    String username="a";
    String storeName="Caves";

    /*@Test//
    public void createShop() throws Exception{
        Bundle b = new Bundle();
        b.putString("username", "UnitTest");
        b.putString("shopName", "Caves");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.txtName)).perform(typeText("UnitTestShop"),closeSoftKeyboard());
        onView(withId(R.id.txtDesc)).perform(typeText("UnitTestShop"),closeSoftKeyboard());
        onView(withId(R.id.txtType)).perform(typeText("UnitTestShop"),closeSoftKeyboard());
        onView(withId(R.id.btnAddShop)).perform(click());
    }*/


}
