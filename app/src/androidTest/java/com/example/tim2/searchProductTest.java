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
public class searchProductTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(searchProduct.class,true,false);

    String username="UnitTest";
    String shopName="UnitTestShop";
    String productName="UnitTestProduct";

    @Test
    public void btnTextView() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"UnitTest").putExtra(shopName,"UnitTestShop").putExtra(productName,"UnitTestProduct"));
        onView(withId(R.id.textView)).check(matches(withText("Please enter the product you are searching for.")));
    }

    @Test
    public void btnDelete() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"UnitTest").putExtra(shopName,"UnitTestShop").putExtra(productName,"UnitTestProduct"));
        onView(withId(R.id.btnDelete)).check(matches(withText("Search Product")));
    }

    @Test//
    public void searchProduct() throws Exception{
        Bundle b = new Bundle();
        b.putString("username", username);
        b.putString("shopName", shopName);
        b.putString("productName", productName);
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.productName_edit_text)).perform(typeText("UnitTestProduct"),closeSoftKeyboard());
        onView(withId(R.id.btnDelete)).perform(click());
    }


}
//Finished