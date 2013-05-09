ExpandableItem
==============

An Expandable Item that can be used for android list views or as a single view. 
The idea is to have a view that can expand with two finger gesture that would show more information or hidden data.

There are two views on top of each other. The view at the bottom can be dragged down with two finger.
If it's inside a list view, it's a great way to hide related information to that item.

Usage:

Create two xml view files.
 - (1) that will be always visible
 - (2) that will be used to show in expanded mode

In your Activity or List Adapter, you can create your Expandable View.
 - ExpandableItem item = new ExpandableItem(context);
 - item.setup = item.new SetupInfo();
 - item.setup.center_layout = (inflate (1) xml file (always visible))
 - item.setup.bottom_layout = (inflate (2) xml file (hidden))
 - item.setup.long_press_expand = true; // enable/disable long press expand (default is true)
 - item.setup.two_finger_drag = true; // enable/disable two finger drag expand (default is true)
 - item.setup.long_press_delay = 1000; //if long press enabled, delay is in milliseconds
 - item.setup.open_listener = this; // the class that implements the ExpandableItem.ItemOpenListener
 - item.setup.id = some_id; // if needed, great for list views
 - item.setup.extra_params.put("", ""); // any thing you want

If you want to expand the item, call item.open(animate)
If you want to collapse the item, call item.close(animate)

* animate is a boolean option, in case if needed. Normally controlling the state of the item within a List Adapter will 
* require use of item.open or item.close, so you would generally set the animate option to false. Otherwise, if you for
* expample have a button that can expand the item, then you can set the animate to true;

ExpandableItem.ItemOpenListener is the interface that provides two callback methods for notifying you when the item 
is opened or closed. It provides you with onItemOpened() and onItemClosed().
* Both methods provide id and refernce to itself as parameters when you needed.

For example, checkout SimpleActivity and SimpleAdapter.
