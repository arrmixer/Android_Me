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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    public static final String EXTRA_ANDROID_HEAD = "com.ae.Android_Me.EXTRA_ANDROID_HEAD";
    public static final String EXTRA_ANDROID_BODY = "com.ae.Android_Me.EXTRA_ANDROID_BODY";
    public static final String EXTRA_ANDROID_LEG = "com.ae.Android_Me.EXTRA_ANDROID_LEG";
    private static final int REQUEST_CODE = 101;

    //Bundle instance to save data for intent
    private Bundle mBundle = new Bundle();

    //placeholders for the indexes
    private int headIndex;
    private int bodyIndex;
    private int legIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int storedPosition;

        final Intent intent = new Intent(this, AndroidMeActivity.class);


        // TODO (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        if(position < 12){
            headIndex = position;
            mBundle.putInt(EXTRA_ANDROID_HEAD, headIndex);
        }else if(position > 11 && position < 24){
            bodyIndex = position - 12;
            mBundle.putInt(EXTRA_ANDROID_BODY, bodyIndex);
        }else{
            legIndex = position - 24;
            mBundle.putInt(EXTRA_ANDROID_LEG, legIndex);
        }


        // TODO (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        intent.putExtras(mBundle);

        // TODO (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        Button mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(requestCode == RESULT_OK){
                headIndex = data.getIntExtra(EXTRA_ANDROID_HEAD,1);
                bodyIndex = data.getIntExtra(EXTRA_ANDROID_BODY, 1);
                legIndex  = data.getIntExtra(EXTRA_ANDROID_LEG, 1);
            }
        }
    }
}
