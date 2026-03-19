ALTER TABLE `login_accounts` CHANGE `password` `password` VARCHAR(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;

INSERT INTO `login_accounts` (`ID`, `type`, `username`, `password`) VALUES
('A01', 'Admin', 'admin', '$2a$12$nhK9Qsh/KcMKP3Yufa9duucRJgsu4yDFuLeRIh9U2N1bY0a8NG98W'),
('R01', 'Reception', 'user', '$2a$12$lJusBen9nhRyOfufCYbOxuVxigmSKfesWxZ8gvUnDPf8YL.JJEzgC');