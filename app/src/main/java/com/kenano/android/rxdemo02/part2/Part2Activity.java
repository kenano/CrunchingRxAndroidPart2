package com.kenano.android.rxdemo02.part2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kenano.android.rxdemo02.R;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by KenanO on 8/13/17.
 */

public class Part2Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    //create a list of strings which will be emitted by the observable we will create.
    final String[] manyWords = {"Hello", "to", "everyone", "from", "RxAndroid",
            "something", "that", "is", "really", "nice"};
    final List<String> manyWordList = Arrays.asList(manyWords);

    // it is not necessary to implement a full subscriber to be created when an observable is
    // subscribed to. All we need is to define the onNext Action. That action will be to set the text.
    Action1<String> textViewOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            txtPart1.setText(s);
        }
    };

    //same as above but in action to emit a Toast. Again not defining a full subscriber.
    Action1<String> toastOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Create a function which will generate an Observabe from the List defined above.
     *
     */
    Func1<List<String>, Observable<String>> getUrls = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            //generates an observable from a List (of Strings).
            return Observable.from(strings);
        }
    };

    /**
     * Creates function which takes a string and returns the String in all caps.
     */
    Func1<String, String> toUpperCase = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    /**
     * Creates a function which merges all received strings into 1.
     */
    Func2<String, String, String> mergeStrings = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s,s2);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

        initObservables();
    }

    private void initObservables() {

        //create an observable which emits 1 String.
        Observable<String> singleObservable = Observable.just("Hello World");

        //get String emitted from above, set it to all caps and display it in UI.
        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(toUpperCase)
                .subscribe(textViewOnNextAction);

        //create an Observable which will emit Strings from a List.
        Observable<String> emitsStringsObservable = Observable.from(manyWordList);

        //display each of these Strings in a Toast
        emitsStringsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(toastOnNextAction);

        //use the Observable emitting strings from a list to merge all the strings emitted
        // into one String.
        //Since an observable can only be subscribed to once create a new instance of one.
        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                //create a new Observable emitting the received Strings
                .flatMap(getUrls)
                .reduce(mergeStrings)
                .subscribe(toastOnNextAction);

    }

    private void initUI() {

        context = this;

        // use same layout as part1
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView) findViewById(R.id.part1text);
    }
}
