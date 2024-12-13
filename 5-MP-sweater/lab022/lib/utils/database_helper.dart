import 'package:sqflite/sqflite.dart';
import 'dart:async';
import 'dart:io';
import 'dart:convert';
import 'package:path_provider/path_provider.dart';
import 'package:http/http.dart' as http;
import 'dart:developer';

import '../models/post.dart';

class DatabaseHelper {

  static DatabaseHelper _databaseHelper;    // Singleton DatabaseHelper
  static Database _database;                // Singleton Database

  String postTable = 'post_table';
  String col_id = 'id';
  String col_userName = 'user_name';
  String col_activityName = 'activity_name';
  String col_memberLimit = 'member_limit';
  String col_currentMembers = 'current_members';
  String col_date = 'date';
  String col_time = 'time';
  String col_location = 'location';
  String col_description = 'description';

  DatabaseHelper._createInstance(); // Constructor

  factory DatabaseHelper() {

    if (_databaseHelper == null) {
      _databaseHelper = DatabaseHelper._createInstance(); // Singleton object
    }
    return _databaseHelper;
  }

  Future<Database> get database async {

    if (_database == null) {
      _database = await initializeDatabase();
    }
    return _database;
  }

  Future<Database> initializeDatabase() async {
    // Get the directory path to store database.
    Directory directory = await getApplicationDocumentsDirectory();
    String path = directory.path + 'post_table.db';

    // Open/create the database at the given path
    var notesDatabase = await openDatabase(path, version: 3, onCreate: _createDb);
    return notesDatabase;
  }

  Future<List<Post>> getData() async {
    var url = "http://10.0.2.2:8080/posts"; //"http://127.0.0.1:8080/posts"
    //http.Response - type
    http.Response response = await http.get(
      Uri.encodeFull(url),
      //Authentication Keys
      headers: {
        "Accept": "application/json"  //Accept Json files
      }
    );
    log("Before decode: " + response.body);
    List data = json.decode(response.body);
    log("After decode: " + data.toString());
    return data;
  }

  void _createDb(Database db, int newVersion) async {
    await db.execute(
        'CREATE TABLE $postTable('
            '$col_id INTEGER PRIMARY KEY AUTOINCREMENT, '
            '$col_userName TEXT, '
            '$col_activityName TEXT, '
            '$col_memberLimit INTEGER, '
            '$col_currentMembers INTEGER, '
            '$col_date TEXT, '
            '$col_time TEXT, '
            '$col_location TEXT, '
            '$col_description TEXT)'
    );
  }

  // Fetch Operation: Get all Post objects from database
  Future<List<Map<String, dynamic>>> getPostMapList() async {
    Database db = await this.database;

    // Order by id
    var result = await db.query(postTable, orderBy: '$col_id ASC');
    return result;
  }

  // Insert Operation: Insert a Post object to database
  Future<int> insertPost(Post post) async {
    Database db = await this.database;
    var result = await db.insert(postTable, post.toMap());
    return result;
  }

  // Update Operation: Update a Post object and save it to database
  Future<int> updatePost(Post post) async {
    var db = await this.database;
    var result = await db.update(postTable, post.toMap(), where: '$col_id = ?', whereArgs: [post.id]);
    return result;
  }

  // Delete Operation: Delete a Post object from database
  Future<int> deletePost(int id) async {
    var db = await this.database;
    int result = await db.rawDelete('DELETE FROM $postTable WHERE $col_id = $id');
    return result;
  }

  // Get number of Post objects in database
  Future<int> getCount() async {
    Database db = await this.database;
    List<Map<String, dynamic>> x = await db.rawQuery('SELECT COUNT (*) from $postTable');
    int result = Sqflite.firstIntValue(x);
    return result;
  }

  // Get the 'Map List' [ List<Map> ] and convert it to 'Post List' [ List<Post> ]
  Future<List<Post>> getPostList() async {

    var postMapList = await getPostMapList(); // Get 'Map List' from database
    int count = postMapList.length;           // Count the number of map entries in db table

    List<Post> postList = List<Post>();
    // Create a 'Post List' from a 'Map List'
    for (int i = 0; i < count; i++) {
      postList.add(Post.fromMapObject(postMapList[i]));
    }

    return postList;
  }

  Future<bool> containsPost(Post post) async {
    List<Post> postList = await getPostList();
    for(int i = 0; i < postList.length; i++)
      if(post.id == postList[i].id)
        return true;
    return false;
  }

}
