package com.vitaran.ngo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {

    private String mName;
    private String mEmail;
    private String mPassword,mPassword1;
    private String mRePassword;
    private String mContact1,mContact2;
    private String mAddress;
    private String mSuccessString;
    private String mExistsString;
    private String mEmptyFieldsString;
    private String mInvalidEmailString;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initVariables(){

        mName = "Ashish";
        mEmail = "ashes@gmail.com";
        mPassword = "12345";
        mPassword1 = "123455";
        mContact1 = "asd123";
        mContact2 = "9748193066";
        mAddress = "qwerty";
        mSuccessString = "User Registered Successfully !";
        mExistsString = "User Already Registered !";
        mEmptyFieldsString = "Fields are empty !";
        mInvalidEmailString = "Invalid Email";
    }

    @Test
    public void test1(){

        onView(withId(R.id.tv_register)).perform(click());

        onView(withId(R.id.et_name)).perform(typeText(mName));

        closeSoftKeyboard();

        onView(withId(R.id.et_email)).perform(typeText(mEmail));

        closeSoftKeyboard();

        onView(withId(R.id.et_contact)).perform(typeText(mContact2));

        closeSoftKeyboard();

        onView(withId(R.id.et_address)).perform(typeText(mAddress));

        closeSoftKeyboard();

        onView(withId(R.id.et_password)).perform(typeText(mPassword));

        closeSoftKeyboard();

        onView(withId(R.id.et_repassword)).perform(scrollTo(), typeText(mPassword));

        //onView(withId(R.id.et_repassword)).perform(typeText(mPassword));

        closeSoftKeyboard();

        onView(withId(R.id.btn_register)).perform(click());

        //onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(mSuccessString))).check(matches(isDisplayed()));


    }

    @Test
    public void test2(){

        onView(withId(R.id.tv_register)).perform(click());

        onView(withId(R.id.et_name)).perform(typeText(mName));


        onView(withId(R.id.et_email)).perform(typeText(mEmail));

        /*onView(withId(R.id.et_contact)).perform(typeText(mContact2));

        onView(withId(R.id.et_address)).perform(typeText(mAddress));

        onView(withId(R.id.et_password)).perform(typeText(mPassword));*/

        onView(withId(R.id.et_repassword)).perform(typeText(mPassword));

        closeSoftKeyboard();


        onView(withId(R.id.btn_register)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(mExistsString))).check(matches(isDisplayed()));
    }

    @Test
    public void test3(){

        onView(withId(R.id.tv_register)).perform(click());

        onView(withId(R.id.et_name)).perform(typeText(mName));

        closeSoftKeyboard();

        onView(withId(R.id.et_email)).perform(typeText(mEmail));

        closeSoftKeyboard();

        onView(withId(R.id.et_contact)).perform(typeText(mContact2));

        closeSoftKeyboard();

        onView(withId(R.id.et_address)).perform(typeText(mAddress));

        closeSoftKeyboard();

        onView(withId(R.id.et_repassword)).perform(typeText(mPassword));

        closeSoftKeyboard();

        onView(withId(R.id.et_password)).perform(typeText(mPassword));

        closeSoftKeyboard();

        onView(withId(R.id.btn_register)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(mInvalidEmailString))).check(matches(isDisplayed()));
    }

   /* @Test
    public void test4CheckFieldsEmpty(){

        onView(withId(R.id.tv_register)).perform(click());

        onView(withId(R.id.btn_register)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(mEmptyFieldsString))).check(matches(isDisplayed()));

    }*/

}
