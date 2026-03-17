import 'dart:io';

void main() {
  print('Nhap ten');
  String? name = stdin.readLineSync();
  int tuoi;
  while(true){
    try {
      print('Nhap tuoi');
      tuoi = int.parse(stdin.readLineSync()!);
      break;
    }catch(e){
      print('phai nhap tuoi la number');
    }
  }

  print('Hello $name, tuoi la $tuoi');
}