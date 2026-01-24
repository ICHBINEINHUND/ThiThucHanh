#include <stdio.h>
int findMax(int arr[], int n) {
  int maxVal = arr[0];
  for (int i = 1; i < n; i++) {
    if (arr[i] > maxVal) {
      maxVal = arr[i];
    }
  }
  return maxVal;
}
int findMin(int arr[], int n) {
  int minVal = arr[0];
  for (int i = 1; i < n; i++) {
    if (arr[i] < minVal) {
      minVal = arr[i];
    }
  }
  return minVal;
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
  printf("The Largest Number In The Array: %d\n", findMax(arr, n));
  printf("The Smallest Number In The Array: %d\n", findMin(arr, n));
  return 0;
}