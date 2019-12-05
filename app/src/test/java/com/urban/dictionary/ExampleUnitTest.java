package com.urban.dictionary;

import com.urban.dictionary.adapter.UrbanDictionaryDataAdapter;
import com.urban.dictionary.model.UrbanDictionaryListModel;
import com.urban.dictionary.ui.fragments.UrbanDictionaryListFragment;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        ArrayList<UrbanDictionaryListModel> urbanDictionaryListModel = new ArrayList<UrbanDictionaryListModel>();
        UrbanDictionaryDataAdapter urbanDictionaryDataAdapter = new UrbanDictionaryDataAdapter();
        UrbanDictionaryListFragment urbanDictionaryListFragment = new UrbanDictionaryListFragment();
        urbanDictionaryDataAdapter.setUrbanDictionaryListModels(urbanDictionaryListModel);
        assertEquals(   urbanDictionaryDataAdapter.getUrbanDictonaryModel(),     urbanDictionaryListFragment.sortByThumbsCount(true));

    }
}