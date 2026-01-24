#include <stdio.h>
struct Student {
  char studentID[20];
  char fullName[100];
  float gpa;
  int yearOfBirth;
};
int main() {
  int n;
  printf("Enter The Number Of Students: ");
  scanf("%d", &n);
  struct Student students[n];
  for (int i = 0; i < n; i++) {
    printf("Enter Information For Student %d:\n", i + 1);
    printf("Student ID: ");
    scanf("%s", students[i].studentID);
    getchar();
    printf("Full Name: ");
    fgets(students[i].fullName, 100, stdin);
    int len = 0;
    while (students[i].fullName[len] != '\0') len++;
    if (len > 0 && students[i].fullName[len - 1] == '\n') {
      students[i].fullName[len - 1] = '\0';
    }
    printf("GPA: ");
    scanf("%f", &students[i].gpa);
    printf("Year Of Birth: ");
    scanf("%d", &students[i].yearOfBirth);
  }
  int maxIdx = 0;
  for (int i = 1; i < n; i++) {
    if (students[i].gpa > students[maxIdx].gpa) {
      maxIdx = i;
    }
  }
  printf("The Student With The Highest GPA:\n");
  printf("Student ID: %s\n", students[maxIdx].studentID);
  printf("Full Name: %s\n", students[maxIdx].fullName);
  printf("GPA: %.1f\n", students[maxIdx].gpa);
  printf("Year Of Birth: %d\n", students[maxIdx].yearOfBirth);
  return 0;
}