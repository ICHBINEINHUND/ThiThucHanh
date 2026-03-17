import 'dart:convert';

class Student {
  String? rollNo;
  String? name;
  int? age;

  Student(this.rollNo, this.name, this.age);

  Student.fromJson(Map<String, dynamic> map){
    rollNo=map["rollNo"];
    name=map["name"];
    age=map["age"];
  }

  Map<String, dynamic> toJson(){
    return {
      "rollNo": rollNo,
      "name": name,
      "age": age,
    };
  }

  void hienThongTin(){
    print('Roll: $rollNo');
    print('Name: $name');
    print('Age: $age');
    print('');
  }
}

class StudentService {
  static List<Student> fromJsons(String rawJsons){
    var list = jsonDecode(rawJsons) as List<dynamic>;
    return list.map((e) => Student.fromJson(e as Map<String, dynamic>))
    .toList();
  }
  static Student fromJson(String rawJson){
    Map<String, dynamic> json = jsonDecode(rawJson) as Map<String, dynamic>;
    // var json = jsonDecode(rawJson) as Map<String, dynamic>;

    return Student.fromJson(json);
  }

  static String toRawJson(Student stu){
    return jsonEncode(stu.toJson());
  }
}