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

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    //  (1) Create a fragment_master_list.xml layout file to display all our images; this should be a GridView

    //  (2) Create a new class called MasterListFragment which will display the GridView list of ALL AndroidMe images
        // In the fragment class, you'll need to implement an empty constructor, and onCreateView

    //  (3) In the MasterListFragment class, create a new MasterListAdapter and set it on the GridView
        // The MasterListAdapter code is provided; it creates the ImageViews that are contained in the GridView
        // The adapter takes as parameters (Context context, List<Integer> imageIds)

    // After creating the fragment..
    //  (4) Create a new Activity named MainActivity and a corresponding layout file that displays a MasterListFragment
        // Remember, to display a static fragment in a layout file, use the <fragment> tag


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        int headIndex = 0, bodyIndex = 0, legIndex = 0;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            headIndex = bundle.getInt("headIndex");
            bodyIndex = bundle.getInt("bodyIndex");
            legIndex = bundle.getInt("legIndex");
        }

        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {

            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();

            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(headIndex);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            // Create and display the body and leg BodyPartFragments

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }

    }
}
