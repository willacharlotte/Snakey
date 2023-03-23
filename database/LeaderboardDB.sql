CREATE DATABASE StatisticsDB
GO

USE [StatisticsDB]

CREATE TABLE [dbo].[Leaderboard] (
  [ID] [int] PRIMARY KEY IDENTITY(1, 1) NOT NULL,
  [userName] [nvarchar](255),
  [score] [int],
  [gameDate] [date]
);
GO

ALTER TABLE [dbo].[Leaderboard] ADD CONSTRAINT [unqUserName] UNIQUE([userName])
ALTER TABLE [dbo].[Leaderboard] ADD CONSTRAINT [chkGameDate] CHECK([gameDate] <= GetDate())