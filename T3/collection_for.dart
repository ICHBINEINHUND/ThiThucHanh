void main() {
  var l = [1,2,4];
  print(doubleIt(l));
  print(doubleIt2(l));
}

List<int> doubleIt(List<int> list) {
  for (int i = 0; i < list.length; i++) {
    list[i] = list[i] * 2;
  }

  return list;
}

List<int> doubleIt2(List<int> list) {
  var newList= <int>[
    for(var it in list) it*2
  ];

  return newList;
}
