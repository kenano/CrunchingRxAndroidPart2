# Crunching RxAndroid Part2-3

This is a Rx tutorial from medium.com
[intro](https://medium.com/crunching-rxandroid/crunching-rxandroid-intro-c27eb6f009ea)
[Part1](https://medium.com/crunching-rxandroid/crunching-rxandroid-part-1-4ac7b7123238)
[Part2](https://medium.com/crunching-rxandroid/crunching-rxandroid-part-2-fb2a86f4fb8d)

We will use strings generated from the list to output to the UI. Instead of defining Observable.Onsubscribe
and Subscribers will define Functions and Actions which tell how to generate the observable and the
Action to be performed when an Observable is subscribed to.

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

## Action1, Action2, etc
These objects implement call but do not return anything. The number refers to how many params call
will have.
Use this instead of creating a subscriber to define the Action to be performed onNext.

## Func1, Func2, etc
This also implements call but  also defines a return type. Therefore Func1 will define a type of
2 parameters, the second being the return type. Func2 would have 3, etc.
