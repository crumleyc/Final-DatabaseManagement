SELECT s.salary, s.playerID, f.POS, f.GS
FROM salaries s, fielding f
WHERE s.playerID=f.playerID
AND f.playerID='sutclri01';

SELECT yearID, H, AB
FROM batting
WHERE playerID = 'sutclri01';

SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

ALTER TABLE people ADD PRIMARY KEY(playerID);

SELECT playerID, nameFirst, nameLast
FROM people
WHERE nameLast='Jones';

