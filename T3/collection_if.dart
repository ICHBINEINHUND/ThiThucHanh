void main() {
  var users1 = layVeUser(true);
  var users2 = layVeUser(false);
  print(users1);
  print(users2);
}

List<String> layVeUser(bool isAdmin) {
  // var users = ["One", "two"];
  // // if(isAdmin){
  // //   users.add("ADMIN");
  // // }else{
  // //   users.add("GUEST");
  // // }
  // String other = isAdmin ? "ADMIN": "GUEST";
  // users.add(other);

  // var users = ["One", "two", if (isAdmin) "ADMIN"];
  var users = ["One", "two", if (isAdmin) "ADMIN" else "GUEST"];

  return users;
}
