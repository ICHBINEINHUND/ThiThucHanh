import 'dart:io';

void main(){
  print('Nhap ten vao');
  String? name = stdin.readLineSync();

  print('Nhap tuoi vao');
  int tuoi = int.parse(stdin.readLineSync()!);

  // if(name != null) {
  //   print("Hello ${name.toUpperCase()}");
  // }
    print("Hello ${name!.toUpperCase()}, tuoi la $tuoi");
}