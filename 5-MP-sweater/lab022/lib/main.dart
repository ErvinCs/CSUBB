import 'package:flutter/material.dart';
import 'screens/post_list.dart';
import 'screens/post_detail.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Sweater',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          primarySwatch: Colors.deepPurple
      ),
      home: PostList(),
    );
  }
}