# Event-portal-android-app

 Used swipe tabs instead of creating different activities to save time and easy usage.

 A PHP script on server makes available the event data in the json format.

 That is parsed and the data is displayed dynamically in different fragments.

 Async task class is used to asynchronously download the data in a different thread.

 For quick glance info, only Event Name, Date, Time is shown for an event. One can touch the 

event to expand the layout, showing complete details. 

 ‘Add Reminder’ button on the event listing exports the event data to the Google Calendar.

 It then asks the user to add the reminder in the calendar with the exported data, or to modify 

the data and then set the reminder.

 Whenever a new event is added on the website, the user gets a push notification even when the 

app is not running.
