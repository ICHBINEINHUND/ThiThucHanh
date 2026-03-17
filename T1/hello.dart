void main(){
  print('hello Dart');
  String name = "Tien Tung";
  // var name = "Tien Tung";
  var addr = 'Ha Noi';

  print('Hello ' + name+", address la: " + addr);
  print('Hello $name, address la: $addr');
  print('Hello ${name.toUpperCase()}, address la: $addr');

  String msg = 'Hello ${name.toUpperCase()}, address la: $addr';
  print(msg);
  //
  const int so = 10;
  final int so2 = 10;

  // so=11;
  // so2=22;
  // DateTime now = DateTime.now();
  final DateTime now = DateTime.now();
  // now = DateTime.now();
  print(now);
  // const DateTime now = DateTime.now();
}