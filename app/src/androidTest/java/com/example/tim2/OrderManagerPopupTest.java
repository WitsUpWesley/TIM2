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
public class OrderManagerPopupTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(OrderManagerPopup.class,true,false);
    String status="Processing", orderNo="40";

    String username="UnitTest";
    String storeName="UnitTestShop";

    @Test
    public void btnNextStage() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"UnitTest").putExtra(storeName,"UnitTestShop").putExtra(status,"Processing").putExtra(orderNo,"40"));
        onView(withId(R.id.btnNextStage)).check(matches(withText("Advance Order Status")));
    }
    @Test
    public void btnCreateProduct() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"UnitTest").putExtra(storeName,"UnitTestShop").putExtra(status,"Processing").putExtra(orderNo,"40"));
        onView(withId(R.id.btnCancelOrder)).check(matches(withText("Cancel Order")));
    }

    @Test
    public void nextCancel() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "UnitTest");
        b.putString("shopName", "UnitTestShop");
        b.putString("status", "Cancelled");
        b.putString("orderNo", "40");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnCancelOrder)).perform(click());
    }

    @Test
    public void nextStage() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "UnitTest");
        b.putString("shopName", "UnitTestShop");
        b.putString("status", "Cancelled");
        b.putString("orderNo", "40");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnNextStage)).perform(click());
    }



}
//Finished