package com.urban.dictionary.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.urban.dictionary.R;
import com.urban.dictionary.ui.fragments.UrbanDictionaryListFragment;

import butterknife.ButterKnife;

public class UrbanDictionaryActivity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "UrbanDictionaryActivity";
    ActionBar ab;
    UrbanDictionaryListFragment urbanDictionaryListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urban_dictionary_list);
        ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void showDictionaryFragment(String term) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle termBundle = new Bundle();
        termBundle.putString("term",term);
        urbanDictionaryListFragment = UrbanDictionaryListFragment.newInstance();
        urbanDictionaryListFragment.setArguments(termBundle);
        ft.replace(R.id.listcontainer, urbanDictionaryListFragment);
        ft.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.thumbs_up: {
                if(urbanDictionaryListFragment!=null)
                urbanDictionaryListFragment.sortByThumbsCount(true);
                break;
            }

            case R.id.thumbs_down: {
                if(urbanDictionaryListFragment!=null)
                    urbanDictionaryListFragment.sortByThumbsCount(false);
                    break;

            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search_item = menu.findItem(R.id.mi_search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search Urban Dictionary");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String term) {

                ab.setDisplayShowTitleEnabled(false);
                showDictionaryFragment(term);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
