package com.example.testingexample;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ZhangYiGe on 2017/1/20 0020.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupRealm();
        setupStetho();
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.deleteRealmIfMigrationNeeded();
        Realm.setDefaultConfiguration(builder.build());
    }

    private void setupStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
