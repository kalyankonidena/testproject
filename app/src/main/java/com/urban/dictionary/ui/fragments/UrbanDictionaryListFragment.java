package com.urban.dictionary.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.urban.dictionary.R;
import com.urban.dictionary.adapter.UrbanDictionaryDataAdapter;
import com.urban.dictionary.model.UrbanDictionaryListModel;
import com.urban.dictionary.model.UrbanDictionaryModel;
import com.urban.dictionary.network.UrbanDisctionalryListService;
import com.urban.dictionary.utils.UrbanDictionaryUtils;
import com.urban.dictionary.utils.UrbanDictionaryProgressDialog;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrbanDictionaryListFragment extends Fragment {


    private CompositeDisposable mCompositeDisposable;

    @BindView(R.id.urban_dictionary_list_recycler_view)
    RecyclerView urbanDictionaryListRecyclerView;

    UrbanDictionaryDataAdapter mAdapter;

    String searchTerm;

    public static UrbanDictionaryListFragment newInstance() {
        return new UrbanDictionaryListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_urban_dictionary_list, container, false);
        ButterKnife.bind(this, view);
        urbanDictionaryListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        urbanDictionaryListRecyclerView.setLayoutManager(layoutManager);
        searchTerm = getArguments().getString("term");
        loadDictionaryList();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    /**
     * This method sorts the the urban list by thumbs up or thumbs down count.
     * @param thumbsCountSelection
     */
    public ArrayList<UrbanDictionaryListModel> sortByThumbsCount(boolean thumbsCountSelection){

        if(mAdapter!=null && mAdapter.getUrbanDictonaryModel()!=null) {
            Collections.sort(mAdapter.getUrbanDictonaryModel(), new Comparator<UrbanDictionaryListModel>() {
                @Override
                public int compare(UrbanDictionaryListModel urbanDictionaryModelLHS,
                                   UrbanDictionaryListModel urbanDictionaryModelRHS) {
                    int sortIndex = 0;

                    if (thumbsCountSelection) {
                        if ((urbanDictionaryModelLHS.getThumbs_up() < urbanDictionaryModelRHS.getThumbs_up())) {
                            sortIndex = -1;
                        } else {
                            sortIndex = 1;
                        }
                    } else {
                        if ((urbanDictionaryModelLHS.getThumbs_down() < urbanDictionaryModelRHS.getThumbs_down())) {
                            sortIndex = -1;
                        } else {
                            sortIndex = 1;
                        }
                    }

                    return sortIndex;
                }
            });
            urbanDictionaryListRecyclerView.getAdapter().notifyDataSetChanged();
        }

        return mAdapter.getUrbanDictonaryModel();
    }

    /**
     * This method will make the api call for urban dictionary service.
     */
    private void loadDictionaryList() {

        UrbanDictionaryProgressDialog.showProgressDialog(getActivity(), getString(R.string.loading_restaurants));
        UrbanDisctionalryListService requestInterface = new Retrofit.Builder()
                .baseUrl(UrbanDictionaryUtils.URBAN_DICTONARY_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UrbanDisctionalryListService.class);

        mCompositeDisposable.add(requestInterface.getDictionaryResults(searchTerm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }


    /**
     * This method will will handle the response by loading the urban dictionary list
     * in to the recycler view.
     * @param urbanDictionaryModel
     */
    private void handleResponse(UrbanDictionaryModel urbanDictionaryModel) {

        mAdapter = new UrbanDictionaryDataAdapter(urbanDictionaryModel.getItems());
        urbanDictionaryListRecyclerView.setAdapter(mAdapter);
        UrbanDictionaryProgressDialog.dismissDialog();
    }

    private void handleError(Throwable error) {
        Toast.makeText(getActivity(), "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStop() {
        super.onStop();
        mCompositeDisposable.clear();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
