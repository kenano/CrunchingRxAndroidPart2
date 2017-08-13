package com.kenano.android.rxdemo02.part1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kenano.android.rxdemo02.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by KenanO on 8/13/17.
 */

public class Part1Activity extends AppCompatActivity{

    private TextView txtPart1;
    private Context context;

    /**
     *  Here we create the Action that will be called when a {@link rx.Subscriber}
     * subscribes to our {@link rx.Observable}
     * When it does the "hello world" string is emited.
     */
    Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {

        //notice "<? super String>" this means that only string and its super class can be passed as
        // a subscriber
        @Override
        public void call(Subscriber<? super String> subscriber) {

            //if onCompleted is called before onNext, onNext not emitted.
            subscriber.onNext("Hello World");
            subscriber.onCompleted();
        }
    };

    /**
     * This performs when action when an event is recevied from its subscribed observable.
     * This sets the textView
     */
    Subscriber<String> textViewSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            txtPart1.setText(s);
        }
    };

    /**
     * Same as above but this display a toast
     */
    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        context = this;

        init();
    }

    private void init() {
        txtPart1 = (TextView) findViewById(R.id.part1text);

        //create an observable using the Action defined above.
        Observable<String> observable = Observable.create(observableAction);

        //since this is a UI action,  observe on the mainThread.
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(textViewSubscriber);
        observable.subscribe(toastSubscriber);


    }
}
