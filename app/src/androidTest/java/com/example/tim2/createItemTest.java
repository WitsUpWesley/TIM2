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
public class createItemTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(createItem.class,true,false);

    String username="a";
    @Test
    public void txtItemName() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.lblItemName)).check(matches(withText("Item Name")));
    }

    @Test
    public void txtItemNameEntered() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.txtItemName)).check(matches(withText("")));
    }

    @Test
    public void txtItemDesc() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.lblItemDesc)).check(matches(withText("Item Description")));
    }

    @Test
    public void txtItemDescEntered() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.txtItemDesc)).check(matches(withText("")));
    }

    @Test
    public void txtQuantity() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.lblQuantity)).check(matches(withText("Quantity")));
    }

    @Test
    public void txtQuantityEntered() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.txtQuantity)).check(matches(withText("")));
    }

    @Test
    public void btnAddShop() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.btnAddItem)).check(matches(withText("ADD ITEM")));
    }

    @Test//
    public void createItem() throws Exception{
        Bundle b = new Bundle();
        b.putString("username", "UnitTest");
        b.putString("shopName", "UnitTestShop");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.txtItemName)).perform(typeText("UnitTestItem"),closeSoftKeyboard());
        onView(withId(R.id.txtItemDesc)).perform(typeText("UnitTestItem"),closeSoftKeyboard());
        onView(withId(R.id.txtQuantity)).perform(typeText("1"),closeSoftKeyboard());
        onView(withId(R.id.btnAddItem)).perform(click());
    }


}
