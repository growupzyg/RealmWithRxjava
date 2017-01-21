package com.example.testingexample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testingexample.R;
import com.example.testingexample.presistence.Person;

import java.util.ArrayList;

/**
 * Created by ZhangYiGe on 2017/1/20 0020.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {
    private ArrayList<Person> mPersons = new ArrayList();

    public PersonListAdapter(ArrayList<Person> mPersons) {
        this.mPersons = mPersons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = mPersons.get(position);
        holder.mTextName.setText(person.getName());
        holder.mTextSex.setText(person.getSex());
        holder.mTextAge.setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView mTextName;
        public TextView mTextSex;
        public TextView mTextAge;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mTextName = (TextView) rootView.findViewById(R.id.text_name);
            this.mTextSex = (TextView) rootView.findViewById(R.id.text_sex);
            this.mTextAge = (TextView) rootView.findViewById(R.id.text_age);
        }

    }
}
