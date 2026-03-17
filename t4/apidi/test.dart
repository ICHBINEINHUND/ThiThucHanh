import 'dart:math';

import 'sinhvien.dart';

Future<void> main() async {
  var sv = SinhVienService();
  var list = await sv.findAll();
  for(SinhVien sv in list){
    sv.hienThongTin();
  }

  print('--------------------------');
  var stu = await sv.findById("R001111111111111111");
  if(stu != null) {
    stu.hienThongTin();
  }else{
    print('Ko tim thay');
  }
}