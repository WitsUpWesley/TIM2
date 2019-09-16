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
public class createProductTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(createProduct.class,true,false);

    String username="a";
    @Test
    public void txtProductName() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.ProductName)).check(matches(withText("Product Name")));
    }

    @Test
    public void txtProductNameEntered() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.productName_editText)).check(matches(withText("")));
    }


    @Test
    public void btnCreateProduct() throws Exception{
        rule.launchActivity(new Intent().putExtra(username,"a"));
        onView(withId(R.id.btnCreateProduct)).check(matches(withText("CREATE")));
    }


}
