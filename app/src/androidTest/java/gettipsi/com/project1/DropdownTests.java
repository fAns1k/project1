package gettipsi.com.project1;

import android.app.KeyguardManager;
import android.content.Context;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import gettipsi.com.project1.action.SelectElementWithNameAction;
import gettipsi.com.project1.action.SetSelectedAction;
import gettipsi.com.project1.action.SetupElementsAction;
import gettipsi.com.project1.matcher.DropdownMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DropdownTests {

    private List<Object> items;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidData() {
        activityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    KeyguardManager mKeyGuardManager = (KeyguardManager) activityRule.getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                    KeyguardManager.KeyguardLock mLock = mKeyGuardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
                    mLock.disableKeyguard();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        items = Arrays.<Object>asList("One", "Two", "Three", "Four");
    }

    @Test
    public void checkForCorrectViews() {
        onView(withId(R.id.dropdown))
                .check(matches(withClassName(endsWith("DropdownContainer"))));
        onView(withId(R.id.dropdownId))
                .check(matches(withClassName(endsWith("Dropdown"))));
    }

    @Test
    public void checkItemsSelection() {
        setupItems();

        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(0))
                .check(matches(withSpinnerText(items.get(0).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(1))
                .check(matches(withSpinnerText(items.get(1).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(2))
                .check(matches(withSpinnerText(items.get(2).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(3))
                .check(matches(withSpinnerText(items.get(3).toString())));

//        SystemClock.sleep(1000);

    }

    @Test
    public void checkItemsWithNameSelection() {
        setupItems();
        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(0).toString()))
                .check(matches(withSpinnerText(items.get(0).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(1).toString()))
                .check(matches(withSpinnerText(items.get(1).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(2).toString()))
                .check(matches(withSpinnerText(items.get(2).toString())));

//        SystemClock.sleep(1000);
        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(3).toString()))
                .check(matches(withSpinnerText(items.get(3).toString())));

//        SystemClock.sleep(1000);
    }

    @Test
    public void checkItemsClickSelection() {
        setupItems();
        String item = items.get(0).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item))));

        String item1 = items.get(1).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item1))));

        String item2 = items.get(2).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item2))));

        String item3 = items.get(3).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(3).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item3))));
    }

    private void setupItems() {
        onView(withId(R.id.dropdownId))
                .perform(new SetupElementsAction(items))
                .check(ViewAssertions.matches(DropdownMatcher.withListSize(items.size())));
    }

}
