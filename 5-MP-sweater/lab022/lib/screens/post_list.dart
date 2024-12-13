import 'dart:async';
import 'package:flutter/material.dart';
import '../models/post.dart';
import '../utils/database_helper.dart';
import '../screens/post_detail.dart';
import 'package:sqflite/sqflite.dart';


class PostList extends StatefulWidget {

  @override
  State<StatefulWidget> createState() {
    return PostListState();
  }
}

class PostListState extends State<PostList> {

  DatabaseHelper databaseHelper = DatabaseHelper();
  List<Post> postList;
  int count = 0;

  @override
  Widget build(BuildContext context) {

    if (postList == null) {
      postList = List<Post>();
      updateListView();
    }

    return Scaffold(

      appBar: AppBar(
        title: Text('Posts'),
      ),

      body: getPostListView(),

      floatingActionButton: FloatingActionButton(
        onPressed: () {
          debugPrint('FAB clicked');
          navigateToDetail(Post('', '', 1, 1, '', '', ''), 'Add Post');
        },

        tooltip: 'Add Post',

        child: Icon(Icons.add),

      ),
    );
  }

  ListView getPostListView() {

    TextStyle titleStyle = Theme.of(context).textTheme.subhead;

    return ListView.builder(
      itemCount: count,
      itemBuilder: (BuildContext context, int position) {
        return Card(
          color: Colors.white,
          elevation: 2.0,
          child: ListTile(

            leading: CircleAvatar(
              backgroundColor: Colors.purpleAccent,
              child: Icon(Icons.play_arrow),
            ),

            title: Text(this.postList[position].activityName, style: titleStyle,),

            subtitle: Text(this.postList[position].date),

            trailing: GestureDetector(
              child: Icon(Icons.delete, color: Colors.grey,),
              onTap: () {
                _delete(context, postList[position]);
              },
            ),


            onTap: () {
              debugPrint("ListTile Tapped");
              navigateToDetail(this.postList[position],'Edit Post');
            },

          ),
        );
      },
    );
  }

  void _delete(BuildContext context, Post post) async {

    int result = await databaseHelper.deletePost(post.id);
    if (result != 0) {
      _showSnackBar(context, 'Post Deleted Successfully');
      updateListView();
    }
  }

  void _showSnackBar(BuildContext context, String message) {

    final snackBar = SnackBar(content: Text(message));
    Scaffold.of(context).showSnackBar(snackBar);
  }

  void navigateToDetail(Post post, String title) async {
    bool result = await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return PostDetail(post, title);
    }));

    if (result == true) {
      updateListView();
    }
  }

  void updateListView() {

    final Future<Database> dbFuture = databaseHelper.initializeDatabase();
    dbFuture.then((database) {

      Future<List<Post>> postListFuture = databaseHelper.getPostList();
      postListFuture.then((postList) {
        setState(() {
          this.postList = postList;
          this.count = postList.length;
        });
      });
    });
  }
}

