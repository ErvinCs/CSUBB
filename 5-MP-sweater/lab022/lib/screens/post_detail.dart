import 'dart:async';
import 'package:flutter/material.dart';
import '../models/post.dart';
import '../utils/database_helper.dart';
import 'package:intl/intl.dart';

class PostDetail extends StatefulWidget {
  final String appBarTitle;
  final Post post;

  PostDetail(this.post, this.appBarTitle);

  @override
  State<StatefulWidget> createState() {
    return PostDetailState(this.post, this.appBarTitle);
  }
}

class PostDetailState extends State<PostDetail> {
  static var _registered = ['Yes', 'No'];

  DatabaseHelper helper = DatabaseHelper();

  String appBarTitle;
  Post post;

  /*
  int _id;  //Auto
  String _username; //Will be usefull later
  String _activityName;
  int _memberLimit;
  int _currentMembers;  //is 1
  String _date;
  String _time;
  String _location;
  String _description;
  */
  TextEditingController activityNameController = TextEditingController();
  TextEditingController memberLimitController = TextEditingController();
  TextEditingController dateController = TextEditingController();
  TextEditingController timeController = TextEditingController();
  TextEditingController locationController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();

  PostDetailState(this.post, this.appBarTitle);

  @override
  Widget build(BuildContext context) {
    TextStyle textStyle = Theme.of(context).textTheme.title;

    activityNameController.text = post.activityName;
    memberLimitController.text = post.memberLimit.toString();
    dateController.text = post.date;
    timeController.text = post.time;
    locationController.text = post.location;
    descriptionController.text = post.description;

    return WillPopScope(
        onWillPop: () {
          // Back navigation button in the navigationBar
          moveToLastScreen();
        },
        child: Scaffold(
          appBar: AppBar(
            title: Text(appBarTitle),
            leading: IconButton(
                icon: Icon(Icons.arrow_back),
                onPressed: () {
                  // Back button in the AppBar
                  moveToLastScreen();
                }),
          ),
          body: Padding(
            padding: EdgeInsets.only(top: 15.0, left: 10.0, right: 10.0),
            child: ListView(
              children: <Widget>[
                // First element
                ListTile(
                  title: DropdownButton(
                      items: _registered.map((String dropDownStringItem) {
                        return DropdownMenuItem<String>(
                          value: dropDownStringItem,
                          child: Text(dropDownStringItem),
                        );
                      }).toList(),
                      style: textStyle,
                      //value: getRegisteredAsString(post.registered),
                      onChanged: (valueSelectedByUser) {
                        setState(() {
                          debugPrint('User selected $valueSelectedByUser');
                          //updateRegisteredAsInt(valueSelectedByUser);
                        });
                      }),
                ),

                // Second Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: activityNameController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in Name Text Field');
                      updateActivityName();
                    },
                    decoration: InputDecoration(
                        labelText: 'Activity Name',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Third Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: memberLimitController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in MemberLimit Text Field');
                      updateMemberLimit();
                    },
                    decoration: InputDecoration(
                        labelText: 'Member Limit',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Third Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: dateController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in Date Text Field');
                      updateDate();
                    },
                    decoration: InputDecoration(
                        labelText: 'Date',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Fourth Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: timeController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in Time Text Field');
                      updateTime();
                    },
                    decoration: InputDecoration(
                        labelText: 'Time',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Fifth Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: locationController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in Location Text Field');
                      updateLocation();
                    },
                    decoration: InputDecoration(
                        labelText: 'Location',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Sixth Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: TextField(
                    controller: descriptionController,
                    style: textStyle,
                    onChanged: (value) {
                      debugPrint('Something changed in Description Text Field');
                      updateDescription();
                    },
                    decoration: InputDecoration(
                        labelText: 'Description',
                        labelStyle: textStyle,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(5.0))),
                  ),
                ),

                // Seventh Element
                Padding(
                  padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                  child: Row(
                    children: <Widget>[
                      Expanded(
                        child: RaisedButton(
                          color: Theme.of(context).primaryColorDark,
                          textColor: Theme.of(context).primaryColorLight,
                          child: Text(
                            'Save',
                            textScaleFactor: 1.5,
                          ),
                          onPressed: () {
                            setState(() {
                              debugPrint("Save button clicked");
                              _save();
                            });
                          },
                        ),
                      ),
                      Container(
                        width: 5.0,
                      ),
                      Expanded(
                        child: RaisedButton(
                          color: Theme.of(context).primaryColorDark,
                          textColor: Theme.of(context).primaryColorLight,
                          child: Text(
                            'Delete',
                            textScaleFactor: 1.5,
                          ),
                          onPressed: () {
                            setState(() {
                              debugPrint("Delete button clicked");
                              _delete();
                            });
                          },
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ));
  }

  void moveToLastScreen() {
    Navigator.pop(context, true);
  }

  void updateTime() {
    post.time = timeController.text;
  }

  void updateDate() {
    post.date = dateController.text;
  }

  void updateMemberLimit() {
    post.memberLimit = int.parse(memberLimitController.text);
  }

  void updateLocation() {
    post.location = locationController.text;
  }

  void updateActivityName() {
    post.activityName = activityNameController.text;
  }

  void updateDescription() {
    post.description = descriptionController.text;
  }

  // Save data to database
  void _save() async {
    moveToLastScreen();

    post.date = DateFormat.yMMMd().format(DateTime.now());
    int result;
    if (post.id != null) {
      // Update operation
      result = await helper.updatePost(post);
    } else {
      // Insert Operation
      result = await helper.insertPost(post);
    }

    if (result != 0) {
      // Success
      _showAlertDialog('Status', 'Post Saved Successfully');
    } else {
      // Failure
      _showAlertDialog('Status', 'Problem Saving Post');
    }
  }

  void _delete() async {
    moveToLastScreen();

    // If the user is trying to delete the NEW POST i.e. he has come to the detail page by pressing the FAB of NoteList page.
    if (post.id == null) {
      _showAlertDialog('Status', 'No Post was deleted');
      return;
    }
    // If the user is trying to delete the old note that already has a valid ID.
    int result = await helper.deletePost(post.id);
    if (result != 0) {
      _showAlertDialog('Status', 'Post Deleted Successfully');
    } else {
      _showAlertDialog('Status', 'Error Occured while Deleting Post');
    }
  }

  void _showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }
}
