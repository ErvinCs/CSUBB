USE Lab05
GO

BEGIN
	IF OBJECT_ID('dbo.Ta', 'U') IS NOT NULL
	DROP TABLE dbo.Ta
	IF OBJECT_ID('dbo.Tb', 'U') IS NOT NULL
	DROP TABLE dbo.Tb
	IF OBJECT_ID('dbo.Tc', 'U') IS NOT NULL
	DROP TABLE dbo.Tc
END

CREATE TABLE Ta(
	aid INT NOT NULL PRIMARY KEY NONCLUSTERED,
	--CL_IDX on a2
	a2 INT,
	--NCL_IDX on (str1, int1)
	str1 VARCHAR(100),
	int1 INT
);

CREATE TABLE Tb(
	bid INT NOT NULL PRIMARY KEY NONCLUSTERED,
	--NCL_IDX on b2
	b2 INT,
	str2 VARCHAR(100),
	int2 INT
);

CREATE TABLE Tc(
	cid INT NOT NULL PRIMARY KEY NONCLUSTERED,

	CONSTRAINT FK_aid FOREIGN KEY (cid)
	REFERENCES Ta(aid),

	CONSTRAINT FK_bid FOREIGN KEY (cid)
	REFERENCES Tb(bid)
);

INSERT INTO Ta
	([aid], [a2], [int1], [str1])
VALUES
	(1, 101, 1, N'AString1'),
	(2, 101, 1, N'STR1'),
	(3, 100, 1, N'STR1'),
	(4, 12, 200, N'AString2'),
	(5, 13, 59, N'STR2')

INSERT INTO Tb
	([bid], [b2], [int2], [str2])
VALUES
	(1, 10, 2, N'BString1'),
	(2, 15, 1, N'STR3'),
	(3, 16, 200, N'STR3'),
	(4, 10, 500, N'BString2'),
	(5, 18, 1, N'STR4')
