package com.utkarshlamba.edibit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView drawerList;
    ActionBarDrawerToggle drawerToggle;
    ProgressDialog pd;
    TextView toolbarTitle;

    static ArrayList<String> questionsList;
    static ArrayList<String> answersList;
    //static QuestionListAdapter adapter;
    static ArrayList<Integer> countList;

    public static String smsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        pd = new ProgressDialog(this);

        String[] drawerListOptions = getResources().getStringArray(R.array.list_options_array);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        ListArrayAdapter drawerAdapter = new ListArrayAdapter(this, drawerListOptions);


        drawerList.setAdapter(drawerAdapter);
        drawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new AvailableFoodsFragment()).commit();

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = null;

                if (position == 0) {

                    fragment = new AvailableFoodsFragment();
                    //toolbarTitle.setText("Wikipedia");
                    fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                } else if (position == 1) {
                    //fragment = new PostFoodActivity();
                    Intent i = new Intent(getApplicationContext(), PostFoodActivity.class);
                    startActivity(i);
                    //toolbarTitle.setText("Wolfram Alpha");
                } else if (position == 2) {

                    Intent i = new Intent(getApplicationContext(), PaymentInfoAcitivity.class);
                    startActivity(i);
                    //toolbarTitle.setText("Ask Question");
                } else if (position == 3) {
                    Intent i = new Intent(getApplicationContext(), PayActivity.class);
                    startActivity(i);
                }
                /**
                 else {
                 if (isNetworkAvailable()) {
                 fragment = new FAQFragment();
                 toolbarTitle.setText("Helpful Questions");
                 } else {
                 Toast.makeText(getApplicationContext(),
                 "This feature is only available with an network connection",
                 Toast.LENGTH_LONG).show();
                 }
                 }
                 */
                if (fragment != null) {
                    fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }

                drawerLayout.closeDrawer(drawerList);
            }
        });

        Button toggleButton = (Button) findViewById(R.id.toggle_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(drawerList);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_info) {
            final FragmentManager fm = getFragmentManager();
            Fragment fragment = new InfoFragment();
            fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }


}
