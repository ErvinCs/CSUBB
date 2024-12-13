USE Lab05
GO

--CI is on a2
--NCI is on (int1, str1)

--CI - Scan
SELECT aid, a2, str1
FROM Ta
WHERE aid = 3	

--CI - Seek
SELECT aid, a2, str1
FROM Ta
WHERE a2 > 100	

--NCI - Seek + Lookup(?)
SELECT *
FROM Ta
WHERE int1 = 1 AND str1 = 'STR1'

--NCI - Scan (not sure tho)
SELECT int1, str1
FROM Ta
GROUP BY str1, int1

--NCI ON (bid, b2);   
SELECT bid, b2
FROM Tb
WHERE b2 = 10

--CREATE VIEW View_1_str_int
--AS
--SELECT int1, str1
--FROM Ta
--UNION ALL
--SELECT int2, str2
--FROM Tb