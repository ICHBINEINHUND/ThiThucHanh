import 'employee_3.dart';
void main(){
  // var emp =  Employee2("Tien Tung");
  var emp = Employee3(
    age: 22,
    name: "Tien Tung",
    address: "Ha Noi"
  );
  print('Name: ${emp.name}');
  print('Addr: ${emp.address}');
  print('Age: ${emp.age}');

  print('');
  emp = Employee3(
      age: 25,
      name: "Tung Tien"
  );

  print('Name: ${emp.name}');
  print('Addr: ${emp.address}');
  print('Age: ${emp.age}');
}
