import 'student.dart';

void main() {
  // var stu = Student("R0011", "Tien Tung", 22);
  // print(StudentService.toRawJson(stu));
  String rawJson = '{"rollNo":"R00111111","name":"Tien Tung","age":22}';
  var student = StudentService.fromJson(rawJson);
  student.hienThongTin();

  print('------------------');
  String rawJsons =
      '[{"rollNo":"R00111111","name":"Tien Tung","age":22},{"rollNo":"R002222","name":"Tung Tien","age":21}]';
  var stuList = StudentService.fromJsons(rawJsons);
  stuList.forEach((st) {
    st.hienThongTin();
  });
}
