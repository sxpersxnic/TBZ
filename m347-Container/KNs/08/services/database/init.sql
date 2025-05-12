SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE Database m347kn08;

use m347kn08;

CREATE TABLE `users` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `amount` int not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

CREATE TABLE `friends` (
  `user_id1` int NOT NULL,
  `user_id2` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;


ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `friends`
  ADD PRIMARY KEY (`user_id1`, `user_id2`);


insert into users (id, name, amount) values (1, 'Rene', 30);
insert into users (id, name, amount) values (2, 'Sam', 87);
insert into users (id, name, amount) values (3, 'Sara', 54);
insert into users (id, name, amount) values (4, 'Yannis', 54);
insert into users (id, name, amount) values (5, 'Sabrina', 22);

insert into friends(user_id1, user_id2) values (1, 3);
insert into friends(user_id1, user_id2) values (1, 5);
insert into friends(user_id1, user_id2) values (1, 4);

insert into friends(user_id1, user_id2) values (3,2);
insert into friends(user_id1, user_id2) values (3,4);

insert into friends(user_id1, user_id2) values (5,2);
insert into friends(user_id1, user_id2) values (5,4);
