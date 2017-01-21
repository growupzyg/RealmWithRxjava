# RealmWithRxjava
使Realm支持Rxjava操作的代码库

**使用方法**

```java

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

```