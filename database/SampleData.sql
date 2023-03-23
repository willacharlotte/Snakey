USE StatisticsDB

INSERT INTO [dbo].[Leaderboard]
([userName],[score],[gameDate])
VALUES 
('palesa',1500,GETDATE()),
('willa',1000,'2023-01-20'),
('jesse',1000,'2023-02-28'),
('raaga',1000,'2023-03-08'),
('billy',200,'2023-02-08'),
('mary',2500,'2023-03-20'),
('lizzy',1000,'2023-02-28'),
('anna',600,'2023-03-08'),
('joe',700,'2023-02-28'),
('moses',500,'2023-03-08')
GO