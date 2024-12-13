class Post {

  int _id;
  String _username;
  String _activityName;
  int _memberLimit;
  int _currentMembers;
  String _date;
  String _time;
  String _location;
  String _description;

  Post(this._username, this._activityName, this._memberLimit, this._currentMembers,
       this._date, this._time, this._location, [this._description]);

  Post.withId(this._username, this._activityName, this._memberLimit, this._currentMembers,
              this._date, this._time, this._location, [this._description]);

  int get id => _id;

  String get username => username;

  String get activityName => _activityName;

  int get memberLimit => _memberLimit;

  int get currentMembers => _currentMembers;

  String get date => _date;

  String get time => _time;

  String get location => _location;

  String get description => _description;

  set username(String newUsername) {
    if (newUsername.length <= 255 && newUsername.length > 0) {
      this._username = newUsername;
    }
  }

  set activityName(String newActivityName) {
    if(newActivityName.length <= 255 && newActivityName.length > 0) {
      this._activityName = newActivityName;
    }
  }

  set memberLimit(int newMemberLimit) {
    if(newMemberLimit > 0) {
      this._memberLimit = newMemberLimit;
    }
  }

  set currentMembers(int newCurrentMembers) {
    if(newCurrentMembers > 0 && newCurrentMembers < this._memberLimit) {
      this._currentMembers = newCurrentMembers;
    }
  }

  set date(String newDate) {
    if(newDate.length <= 255 && newDate.length > 0) {
      this._date = newDate;
    }
  }

  set time(String newTime) {
    if(newTime.length <=255 && newTime.length > 0) {
      this._time = newTime;
    }
  }

  set location(String newLocation) {
    if(newLocation.length <= 255 && newLocation.length > 0) {
      this._location = newLocation;
    }
  }

  set description(String newDescription) {
    if (newDescription.length <= 255) {
      this._activityName = newDescription;
    }
  }

  // Convert a Post object into a Map object
  Map<String, dynamic> toMap() {

    var map = Map<String, dynamic>();
    if (id != null) {
      map['id'] = _id;
    }
    map['user_name'] = _username;
    map['activity_name'] = _activityName;
    map['member_limit'] = _memberLimit;
    map['current_members'] = _currentMembers;
    map['date'] = _date;
    map['time'] = _time;
    map['location'] = _location;
    map['description'] = _description;

    return map;
  }

  // Extract a Post object from a Map object
  Post.fromMapObject(Map<String, dynamic> map) {
    this._id = map['id'];
    this._username = map['user_name'];
    this._activityName = map['activity_name'];
    this._memberLimit = map['member_limit'];
    this._currentMembers = map['current_members'];
    this._date = map['date'];
    this._time = map['time'];
    this._location = map['location'];
    this._description = map['description'];
  }
}
