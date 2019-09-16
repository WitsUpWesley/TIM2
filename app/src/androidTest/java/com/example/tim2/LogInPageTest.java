package com.example.tim2;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LogInPageTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(LogInPage.class,true,false);

    @Test
    public void txtUser() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.username_editText)).check(matches(withText("")));
    }

    @Test
    public void txtPass() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.password_editText)).check(matches(withText("")));
    }

    @Test
    public void loginPassword() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.password_textView)).check(matches(withText("PASSWORD")));
    }

    @Test
    public void loginUsername() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.username_textView)).check(matches(withText("USERNAME")));
    }

    @Test
    public void btnLogin() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.login_button)).check(matches(withText("LOGIN")));
    }

    @Test
    public void btnSignUp() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.signup_button)).check(matches(withText("SIGN UP")));
    }
}
