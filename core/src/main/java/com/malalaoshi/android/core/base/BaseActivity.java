package com.malalaoshi.android.core.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by kang on 16/12/7.
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        rxBusObservers();
        rxBusPost();
    }

    private void rxBusPost() {
        //findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        RxBus.getInstance().post(HandleEvent.getInstance());
        //    }
        //});
    }
    public void addSubscription(Subscription subscription) {
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(subscription);
    }
    private void rxBusObservers() {
      /*  Subscription subscription = RxBus.getInstance()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Object event) {
                        //if (event instanceof HandleEvent) {
                        //    //do something
                       //     Log.d("wxl", "rxBusHandle");
                        //}
                    }
                });
        addSubscription(subscription);*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.compositeSubscription != null) {
            //取消注册，以避免内存泄露
            this.compositeSubscription.unsubscribe();
        }
    }
}
