#include <stdio.h>
int sumOfOdds(int arr[], int n) {
  int sum = 0;
  for (int i = 0; i < n; i++) {
    if (arr[i] % 2 != 0) {
      sum += arr[i];
    }
  }
  return sum;
}
int main() {
  int n;
  printf("Enter The Number Of Elements: ");
  scanf("%d", &n);
  int arr[n];
  printf("Enter The Array: ");
  for (int i = 0; i < n; i++) {
    scanf("%d", &arr[i]);
  }
  int result = sumOfOdds(arr, n);
  printf("The Sum Of Odd Numbers In The Array Is: %d\n", result);
  return 0;
}