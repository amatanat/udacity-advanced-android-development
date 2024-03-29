/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.teatime;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Activity.RESULT_OK;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

//  (1) Add annotation to specify AndroidJUnitRunner class as the default test runner
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final String emailMessage = "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!";

    //  (2) Add the rule that indicates we want to use Espresso-Intents APIs in functional UI tests
    @Rule
    public IntentsTestRule<OrderSummaryActivity> orderSummaryActivityIntentsTestRule =
            new IntentsTestRule<>(OrderSummaryActivity.class);


    //  (3) Finish this method which runs before each test and will stub all external
    // intents so all external intents will be blocked
    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal()))
                .respondWith(new Instrumentation.ActivityResult(RESULT_OK, null));
    }


    //  (4) Finish this method which verifies that the intent sent by clicking the send email
    // button matches the intent sent by the application
    @Test
    public void clickSendEmailButton_SendsEmail() {

        onView(withId(R.id.send_email_button)).perform(click());

        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_TEXT, emailMessage)));
    }
}