## Worky
Worky is an app for monitoring work activity

You can download the APK [here](https://github.com/Tofira/Worky/blob/master/worky.apk?raw=true)


![Alt Text](https://raw.githubusercontent.com/Tofira/Worky/master/worky_gif.gif)



![Alt Text](https://raw.githubusercontent.com/Tofira/Worky/master/main_screen.png)

Worky has two main screens - 
1. Entrance page - The user can enter his work address.
2. Main page - Displays the list of the user's work hours.

### Architecture

The general Architecture of Worky is presented in the below diagram - 

![Alt Text](https://raw.githubusercontent.com/Tofira/Worky/master/Worky%20Diagram.png)

It uses the (rather) new Android Architecture Components (Room, LiveData & ViewModel).

### Logic
* Whenever a work entrance event is occurred, it's kept in the app's SharedPreferences. This relies on the assumption that there can be only one ongoing event at each time.
* Whenever a work exit event is detected, the app completes the current session.

### Exceptions Handling
Exceptions can be split into two types - 
* An error has occurred in the monitoring sequence (i.e two entrance events in a row, two exits events in a row).
* A session's duration is less than the minimum threshold (Currently 4 hours).

Exceptions are marked by red rows in the main sessions list.

### Architecture Components vs. ContentProvider
In order to implement the persistant data logic, I had to decide between two methods - 
* The Android Architecture Components
* Use ContentProvider, Loaders & Cursors.

Both methods supply an abstraction over the traditional SQLite, and both of them allows for automatic updates to relevant observers.

I chose to use Architecture Components because they supplied me with a cleaner, more comprehensive and compact code. In addition, I didn't have to worry about my data being exposed to other apps (which may happen with ContentProvider).
Also, I wanted to explore new things in the Android world :)
