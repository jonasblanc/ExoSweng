package ch.epfl.sweng.project;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ch.epfl.sweng.project", appContext.getPackageName());
    }

    @Test
    public void nameEnterIsInIntent() {
        Intents.init();

        Espresso.onView(ViewMatchers.withId(R.id.mainName))
                .perform(click())
                .perform(typeText("DD"))
                .perform(closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.mainGoButton))
                .perform(click());


        List<Intent> intents = Intents.getIntents();
        assertThat(intents, not(empty()));
        Intent intent = intents.get(0);//Not very clean but do not see how to do better=> use hasItem(intent such that ...)
        assertThat(intent.getExtras().getString(GreetingActivity.EXTRA_USER_NAME), equalTo("DD"));

        /*
        boolean contains = false;
        for(int i = 0; i < intents.size(); ++ i){
            if(intents.get(i).getStringExtra(GreetingActivity.EXTRA_USER_NAME) == "DD"){
                contains = true;
            }
        }
        assertEquals(true, contains);

         */
        Intents.release();




;    }



}