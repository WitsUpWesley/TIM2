package com.example.tim2;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class SignUpTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(SignUp.class,true,false);

    @Test
    public void txtUsername() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.textView2)).check(matches(withText("Enter username")));
    }

    @Test
    public void fieldUsername() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.enterUsername_editText)).check(matches(withText("")));
    }

    @Test
    public void txtPassword() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.textView4)).check(matches(withText("Enter Password")));
    }

    @Test
    public void fieldPassword() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.enterPassword_editText)).check(matches(withText("")));
    }

    @Test
    public void txtConfirmP() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.textView5)).check(matches(withText("Confirm Password")));
    }

    @Test
    public void fieldConfirmP() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.confirmPassword_editText)).check(matches(withText("")));
    }

    @Test
    public void btnCreate() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.create_button)).check(matches(withText("CREATE")));
    }

    @Test//
    public void signUp() throws Exception {
        rule.launchActivity(new Intent());
        onView(withId(R.id.enterUsername_editText)).perform(typeText("UnitTest"),closeSoftKeyboard());
        onView(withId(R.id.enterPassword_editText)).perform(typeText("UnitTest"),closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText)).perform(typeText("UnitTest"),closeSoftKeyboard());
        onView(withId(R.id.create_button)).perform(click());
    }
}
