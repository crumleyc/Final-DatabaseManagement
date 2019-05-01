ALTER TABLE people ADD PRIMARY KEY (playerID);

-- To drop the 'ONLY_FULL_GROUP_BY' option
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
