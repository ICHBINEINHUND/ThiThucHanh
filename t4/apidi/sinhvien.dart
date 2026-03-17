import 'dart:convert';
import 'package:http/http.dart' as http;

class SinhVien {
  String? id;
  String? name;
  int? age;

  SinhVien(this.id, this.name, this.age);

  SinhVien.fromJson(Map<String, dynamic> map){
    id=map["id"];
    name=map["name"];
    age=map["age"];
  }

  Map<String, dynamic> toJson(){
    return {
      "id": id,
      "name": name,
      "age": age,
    };
  }

  void hienThongTin(){
    print('id: $id');
    print('Name: $name');
    print('Age: $age');
    print('');
  }
}

class SinhVienService {
  Future<List<SinhVien>> findAll() async {
    var uri = Uri.http("localhost:3000", "students");
    var res = await http.get(uri);
    String body = res.body;
    print('body klist student:: $body');

    var json = jsonDecode(body) as List<dynamic>;
    return json.map((e) => SinhVien.fromJson(e as Map<String, dynamic>))
    .toList();
  }
   Future<SinhVien?> findById(String id) async {
     var uri = Uri.http("localhost:3000", "students/$id");
     var res = await http.get(uri);
     if(res.statusCode == 404){
       return null;
     }

     String body = res.body;
     print('body 1 student:: $body');

     var json = jsonDecode(body) as Map<String, dynamic>;
     return SinhVien.fromJson(json);
  }
}