void main() {
  // var rs = fetchNow();
  // rs.then((value) => print('Ket qua $value'));

  fetchNow()
  .then((value) => print('Ket qua $value'));
}

Future<String> fetchNow() {
  print('waiting.....');
  var res = Future.delayed(Duration(seconds: 5))
      .then((value) => "Now is ${DateTime.now()}");
  print('DONE');

  return res;
}