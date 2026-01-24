#include <stdio.h>
int main() {
  int n, isPrime = 1;
  printf("Enter N: ");
  scanf("%d", &n);
  if (n <= 1) {
    isPrime = 0;
  } else {
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        isPrime = 0;
        break;
      }
    }
  }
  if (isPrime) {
    printf("%d Is A Prime Number.\n", n);
  } else {
    printf("%d Is Not A Prime Number.\n", n);
  }
  return 0;
}