Future<void> main() async {
  var value = await fetchNow();
  print('Ket qua $value');
}

Future<String> fetchNow() {
  print('waiting.....');
  var res = Future.delayed(Duration(seconds: 5))
      .then((value) => "Now is ${DateTime.now()}");
  print('DONE');

  return res;
}
