# DLMMS API Testing Guide

## Base URL
```
https://localhost:7114/api
```

---

## 1. Users API

### GET All Users
```
GET /api/Users
```

### GET User by ID
```
GET /api/Users/1
```

### ✅ CheckLogin (Special Method)
```
GET /api/Users/CheckLogin?UserName=Admin&Password=123456
```

### POST - Create User
```
POST /api/Users
Content-Type: application/json

{
  "usersID": 100,
  "userName": "newuser",
  "password": "mypassword",
  "description": "New test user"
}
```

### PUT - Update User
```
PUT /api/Users/100
Content-Type: application/json

{
  "usersID": 100,
  "userName": "updateduser",
  "password": "newpassword",
  "description": "Updated description"
}
```

### DELETE User
```
DELETE /api/Users/100
```

---

## 2. Diploma API

### GET All Diplomas
```
GET /api/Diploma
```

### GET Diploma by ID
```
GET /api/Diploma/1
```

### ✅ Search (Special Method)
```
GET /api/Diploma/Search?strsearch=Hà Nội
GET /api/Diploma/Search?strsearch=Nguyễn
```

### POST - Create Diploma
```
POST /api/Diploma
Content-Type: application/json

{
  "fullName": "Nguyễn Văn Test",
  "dob": "2000-05-15T00:00:00",
  "sexID": 0,
  "trainingTypeID": 8,
  "trainingProgramID": 10,
  "rankID": 1,
  "diplomaTypeID": 1,
  "birthPlace": "Hà Nội",
  "volumedBook": "2",
  "decideCode": "999/QĐ",
  "numberSign": "9999",
  "registerNumber": "9999",
  "language": "Việt",
  "colour": "Nâu",
  "size": "12x16"
}
```

### PUT - Update Diploma
```
PUT /api/Diploma/101
Content-Type: application/json

{
  "diplomaID": 101,
  "fullName": "Nguyễn Văn Updated",
  "dob": "2000-05-15T00:00:00",
  "sexID": 1,
  "trainingTypeID": 8,
  "trainingProgramID": 12,
  "rankID": 2,
  "diplomaTypeID": 1,
  "birthPlace": "Hải Phòng",
  "volumedBook": "2",
  "decideCode": "999/QĐ",
  "numberSign": "9999",
  "registerNumber": "9999",
  "language": "Việt",
  "colour": "Đỏ",
  "size": "12x16"
}
```

### DELETE Diploma
```
DELETE /api/Diploma/101
```

---

## 3. DiplomaType API

### GET All
```
GET /api/DiplomaType
```

### GET by ID
```
GET /api/DiplomaType/1
```

### POST - Create DiplomaType
```
POST /api/DiplomaType
Content-Type: application/json

{
  "diplomaTypeName": "Bằng Tiến sĩ",
  "organizationIssue": "ĐH Ngoại Ngữ",
  "personIssue": "Nguyễn Văn A",
  "position": "Hiệu trưởng",
  "language": "Việt",
  "colour": "Đỏ",
  "size": "18x25"
}
```

### PUT - Update DiplomaType
```
PUT /api/DiplomaType/12
Content-Type: application/json

{
  "diplomaTypeID": 12,
  "diplomaTypeName": "Bằng Tiến sĩ Updated",
  "organizationIssue": "ĐH Ngoại Ngữ",
  "personIssue": "Trần Văn B",
  "position": "Phó Hiệu trưởng",
  "language": "Việt - Anh",
  "colour": "Xanh",
  "size": "18x25"
}
```

### DELETE DiplomaType
```
DELETE /api/DiplomaType/12
```

---

## 4. Rank API

### GET All
```
GET /api/Rank
```

### GET by ID
```
GET /api/Rank/1
```

### POST - Create Rank
```
POST /api/Rank
Content-Type: application/json

{
  "rankName": "Fail",
  "fromPoint": 0,
  "toPoint": 5
}
```

### PUT - Update Rank
```
PUT /api/Rank/6
Content-Type: application/json

{
  "rankID": 6,
  "rankName": "Fail - Updated",
  "fromPoint": 0,
  "toPoint": 4
}
```

### DELETE Rank
```
DELETE /api/Rank/6
```

---

## 5. Sex API

### GET All
```
GET /api/Sex
```

### GET by ID
```
GET /api/Sex/0
```

### POST - Create Sex
```
POST /api/Sex
Content-Type: application/json

{
  "sexID": 2,
  "sexName": "Khác"
}
```

### PUT - Update Sex
```
PUT /api/Sex/2
Content-Type: application/json

{
  "sexID": 2,
  "sexName": "Other"
}
```

### DELETE Sex
```
DELETE /api/Sex/2
```

---

## 6. TrainingProgram API

### GET All
```
GET /api/TrainingProgram
```

### GET by ID
```
GET /api/TrainingProgram/10
```

### POST - Create TrainingProgram
```
POST /api/TrainingProgram
Content-Type: application/json

{
  "trainingProgramName": "Ngôn ngữ Thái Lan",
  "maxPoint": 10,
  "achievePoint": 5,
  "achieve": 0,
  "trainingTypeID": 8
}
```

### PUT - Update TrainingProgram
```
PUT /api/TrainingProgram/51
Content-Type: application/json

{
  "trainingProgramID": 51,
  "trainingProgramName": "Ngôn ngữ Thái Lan - Updated",
  "maxPoint": 10,
  "achievePoint": 6,
  "achieve": 50,
  "trainingTypeID": 8
}
```

### DELETE TrainingProgram
```
DELETE /api/TrainingProgram/51
```

---

## 7. TrainingType API

### GET All
```
GET /api/TrainingType
```

### GET by ID
```
GET /api/TrainingType/8
```

### POST - Create TrainingType
```
POST /api/TrainingType
Content-Type: application/json

{
  "trainingTypeCode": "X",
  "trainingTypeName": "Đào tạo từ xa online"
}
```

### PUT - Update TrainingType
```
PUT /api/TrainingType/11
Content-Type: application/json

{
  "trainingTypeID": 11,
  "trainingTypeCode": "X",
  "trainingTypeName": "Đào tạo từ xa online - Updated"
}
```

### DELETE TrainingType
```
DELETE /api/TrainingType/11
```

---

## Test Checklist

| API | GET All | GET ID | POST | PUT | DELETE | CheckLogin/Search |
|-----|---------|--------|------|-----|--------|-------------------|
| Users | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ CheckLogin |
| Diploma | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ Search |
| DiplomaType | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | - |
| Rank | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | - |
| Sex | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | - |
| TrainingProgram | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | - |
| TrainingType | ⬜ | ⬜ | ⬜ | ⬜ | ⬜ | - |
