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
public class ownShopTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(ownShop.class,true,false);

    String username="a";
    String storeName="Caves";

    @Test
    public void btnCreateItem() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a").putExtra(storeName,"Caves"));
        onView(withId(R.id.btnCreateItem)).check(matches(withText("CREATE ITEM")));
    }
    @Test
    public void btnCreateProduct() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a").putExtra(storeName,"Caves"));
        onView(withId(R.id.btnCreateProduct)).check(matches(withText("CREATE PRODUCT")));
    }
    @Test
    public void btnViewItems() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a").putExtra(storeName,"Caves"));
        onView(withId(R.id.btnViewItems)).check(matches(withText("VIEW ITEMS")));
    }

    @Test
    public void createItemOpening() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("shopName", "Toys Were Us");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnCreateItem)).perform(click());
    }

    @Test
    public void createProductOpening() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("shopName", "Toys Were Us");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnCreateProduct)).perform(click());
    }

    @Test
    public void viewItemOpening() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("shopName", "Toys Were Us");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnViewItems)).perform(click());
    }

    @Test
    public void viewProductOpening() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "a");
        b.putString("shopName", "Toys Were Us");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnViewProducts)).perform(click());
    }

    @Test
    public void viewOrders() throws Exception {

        Bundle b = new Bundle();
        b.putString("username", "UnitTest");
        b.putString("shopName", "UnitTestShop");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.btnViewShopOrders)).perform(click());
    }



}
//Finished