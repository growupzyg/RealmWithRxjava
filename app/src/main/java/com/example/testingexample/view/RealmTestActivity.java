package com.example.testingexample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.testingexample.R;
import com.example.testingexample.presistence.Person;
import com.example.testingexample.view.adapter.PersonListAdapter;
import com.example.testingexample.view.rxjava.RealmObservable;
import com.example.testingexample.widget.RecycleViewDivider;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class RealmTestActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerPersonList;
    private PersonListAdapter mPersonListAdapter;
    private ArrayList<Person> mPersons = new ArrayList<>();
    private Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_test);
        initView();
        dealEvent();
    }

    private void initView() {
        mRecyclerPersonList = (RecyclerView) findViewById(R.id.recycler_person_list);
        mRecyclerPersonList.setOnClickListener(this);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
    }

    private void dealEvent() {
        mPersonListAdapter = new PersonListAdapter(mPersons);
        mRecyclerPersonList.setAdapter(mPersonListAdapter);
        mRecyclerPersonList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerPersonList.addItemDecoration(new RecycleViewDivider(this, LinearLayout.VERTICAL));
        queryPersons();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                RealmObservable
                        .createObservable(new Func1<Realm, Void>() {
                            @Override
                            public Void call(Realm realm) {
                                Logger.d("realm add start");
                                Person person = realm.createObject(Person.class);
                                person.setName("zhangsan" + System.currentTimeMillis());
                                person.setSex("男");
                                person.setAge(24);
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                Logger.d("realm add finish");
                                //查询人员信息
                                queryPersons();
                            }
                        });

                break;
        }
    }

    /**
     * 查询人员信息
     */
    private void queryPersons() {
        RealmObservable
                .createObservable(new Func1<Realm, List<Person>>() {
                    @Override
                    public List<Person> call(Realm realm) {
                        RealmResults<Person> personRealmResults = realm.where(Person.class)
                                .findAll();
                        return personRealmResults;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Person>>() {
                    @Override
                    public void call(List<Person> persons) {
                        mPersons.clear();
                        for (Person person : persons) {
                            mPersons.add(person);
                        }
                        mPersonListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
