package com.urban.dictionary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.urban.dictionary.R;
import com.urban.dictionary.model.UrbanDictionaryListModel;

import java.util.ArrayList;

public class UrbanDictionaryDataAdapter extends RecyclerView.Adapter<UrbanDictionaryDataAdapter.ViewHolder> {

    private ArrayList<UrbanDictionaryListModel> urbanDictionaryListModels;


    public UrbanDictionaryDataAdapter(ArrayList<UrbanDictionaryListModel> gittRepositoryItemsModels) {
        urbanDictionaryListModels = gittRepositoryItemsModels;

    }

    public UrbanDictionaryDataAdapter() {

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.urban_dictionary_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    public void add(UrbanDictionaryListModel gitRepositoryItemsModel) {
        urbanDictionaryListModels.add(gitRepositoryItemsModel);
        notifyItemInserted(urbanDictionaryListModels.size() - 1);
    }

    public void addAll(ArrayList<UrbanDictionaryListModel> mGitRepositoryItemsModels) {
        for (UrbanDictionaryListModel gitRepositoryItemsModel : mGitRepositoryItemsModels) {
            add(gitRepositoryItemsModel);
        }
    }

    public void setUrbanDictionaryListModels(ArrayList<UrbanDictionaryListModel> murbanDictionaryListModels){
        urbanDictionaryListModels = murbanDictionaryListModels;
    }

    public ArrayList<UrbanDictionaryListModel> getUrbanDictonaryModel(){
        return urbanDictionaryListModels;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UrbanDictionaryListModel gitRepositoryItemsModel = urbanDictionaryListModels.get(position);


        holder.defnition.setText(gitRepositoryItemsModel.getDefinition());

        holder.thumbsUp.setText(String.valueOf(gitRepositoryItemsModel.getThumbs_up()));

        holder.thumbsDown.setText(String.valueOf(gitRepositoryItemsModel.getThumbs_down()));

    }

    @Override
    public int getItemCount() {
        return urbanDictionaryListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView defnition;
        private TextView thumbsUp;
        private TextView thumbsDown;

        public ViewHolder(View view) {
            super(view);

            defnition = (TextView) view.findViewById(R.id.defnition);
            thumbsUp = (TextView) view.findViewById(R.id.thumbsup);
            thumbsDown = (TextView) view.findViewById(R.id.thumbsdown);

        }


    }

}
