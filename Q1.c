#include <stdio.h>
int main() {
  int n;
  printf("Enter N: ");
  scanf("%d", &n);
  int isEven = (n % 2 == 0);
  int inRange = (n >= 10 && n <= 100);
  if (isEven && inRange) {
    printf("%d Is An Even Number And Is In The Range 10-100.\n", n);
  } else if (!isEven && inRange) {
    printf("%d Is An Odd Number And Is In The Range 10-100.\n", n);
  } else {
    printf("%d Does Not Meet The Conditions.\n", n);
  }
  return 0;
}