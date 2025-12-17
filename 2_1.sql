CREATE TABLE `t_mark` (
  `id` int(11) NOT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `core_value` float DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `t_mark` (`id`, `subject`, `core_value`, `student_id`) VALUES
(1, 'Math', 85, 1),
(2, 'English', 92, 1),
(3, 'Physics', 78, 1),
(4, 'Math', 88, 2),
(5, 'English', 75, 2),
(6, 'Chemistry', 82, 2),
(7, 'Math', 90, 3),
(8, 'Physics', 85, 3),
(9, 'History', 70, 3),
(10, 'Math', 73, 4),
(11, 'English', 80, 4),
(12, 'Biology', 77, 4),
(13, 'Math', 95, 5),
(14, 'Chemistry', 88, 5),
(15, 'Literature', 90, 5);


CREATE TABLE `t_student` (
  `id` int(11) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `t_student` (`id`, `full_name`, `class_name`, `date_of_birth`) VALUES
(1, 'Alice Nguyen', '10A1', '2008-04-12 00:00:00'),
(2, 'Bob Tran', '10A2', '2008-08-25 00:00:00'),
(3, 'Charlie Le', '10A1', '2008-11-03 00:00:00'),
(4, 'Diana Pham', '10A3', '2009-01-19 00:00:00'),
(5, 'Ethan Hoang', '10A2', '2008-07-05 00:00:00');


ALTER TABLE `t_mark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_student_mark` (`student_id`);

ALTER TABLE `t_student`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `t_mark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;


ALTER TABLE `t_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;


ALTER TABLE `t_mark`
  ADD CONSTRAINT `FK_student_mark` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`);

