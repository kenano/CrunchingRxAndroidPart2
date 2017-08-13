# Crunching RxAndroid Part Intro-Part1

This is a Rx tutorial from medium.com
[intro](https://medium.com/crunching-rxandroid/crunching-rxandroid-intro-c27eb6f009ea)
[Part1](https://medium.com/crunching-rxandroid/crunching-rxandroid-part-1-4ac7b7123238)

We will create an Observable which emits a String. "Hello World"

## install Rx
In app build.gradle
```
compile ‘io.reactivex:rxandroid:0.24.0'
```

## project structure
The MainActivity will have buttons to launch each of the sub-projects

## Observable.OnSubscribe
Used to create an observable. With it we define what is sent with onNext, onCompleted, onError
Pass it to Observable.create() to create an observable.

## Subscriber<String> textViewSubscriber and Subscriber<String> toastSubscriber
Performs a UI action when onNext is received.
Call observable.subscribe to trigger onNext event(s)
