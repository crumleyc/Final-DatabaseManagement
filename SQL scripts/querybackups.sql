-- top 10 home run average for above average weight players:
SELECT b.playerID, p.nameFirst, p.nameLast, AVG(HR), AVG(weight) as weight
FROM batting b
INNER JOIN people p ON b.playerID = p.playerID
WHERE b.playerID IN (
	SELECT DISTINCT(playerID) FROM people 
	WHERE weight > (SELECT AVG(weight) FROM people)
)
GROUP BY b.playerID
ORDER BY AVG(HR) desc
LIMIT 10;

-- top 10 home run average for below average weight players:
SELECT b.playerID, p.nameFirst, p.nameLast, AVG(HR), AVG(weight) as weight
FROM batting b
INNER JOIN people p ON b.playerID = p.playerID
WHERE b.playerID IN (
	SELECT DISTINCT(playerID) FROM people 
	WHERE weight <= (SELECT AVG(weight) FROM people)
)
GROUP BY b.playerID
ORDER BY AVG(HR) desc
LIMIT 10;

-- Given a player, find the season where he had the highest batting average and report the difference from the historical average.
SELECT b.playerID, p.nameFirst, p.nameLast, b.yearID, b.stint, MAX(b.H/b.AB) as playerAvg, b3.hAvg, (MAX(b.H/b.AB) - b3.hAvg) as diff_from_avg
FROM batting b
CROSS JOIN (SELECT AVG(b2.battingAverage) as hAvg  FROM (
			SELECT (H/AB) as battingAverage FROM batting WHERE AB >= 500) as b2
) as b3
INNER JOIN people p ON b.playerID = p.playerID
WHERE b.playerID = 'pujolal01'
GROUP BY b.yearID, b.stint
ORDER BY MAX(b.H/b.AB) DESC
LIMIT 1;