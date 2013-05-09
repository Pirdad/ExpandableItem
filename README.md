ExpandableItem
==============

An Expandable Item that can be used for android list views or as a single view. 
The idea is to have a view that can expand with two finger gesture that would show more information or hidden data.

There are two views on top of each other. The view at the bottom can be dragged down with two finger.
If it's inside a list view, it's a great way to hide related information to that item.

Usage:

Create two xml view files.
 - 1 that will be always visible
 - 1 that will be used to show in expanded mode

In your Activity or List Adapter, you can create your Expandable View.
 - ExpandableItem item = new ExpandableItem(context);
 - ExpandableItem.SetupInfo setup = item.new SetupInfo();
 - 
