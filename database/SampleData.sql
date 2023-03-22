USE StatisticsDB

INSERT INTO [dbo].[Leaderboard]
([userName],[score],[gameDate])
VALUES 
('Palesa',1500,GETDATE()),
('Willa',1000,'2023-01-20'),
('Jesse',1000,'2023-02-28'),
('Raaga',1000,'2023-03-08'),
('Billy',200,'2023-02-08'),
('Mary',2500,'2023-03-20'),
('Lizzy',1000,'2023-02-28'),
('Anna',600,'2023-03-08'),
('Joe',700,'2023-02-28'),
('Moses',500,'2023-03-08')
GO