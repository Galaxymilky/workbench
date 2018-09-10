-- 1.
SELECT id
FROM STUDENT
WHERE length(givenName) + length(familyName) >=
      (SELECT MAX(length(givenName) + length(familyName))
       FROM STUDENT)
;

SELECT
  concat(givenName, familyName),
  id
FROM student
WHERE length(concat(givenName, familyName)) = (
  SELECT max(length(concat(a.givenName, a.familyName)))
  FROM student a
)
;

-- 2.
SELECT S.givenName,S.familyName
FROM STUDENT S,AVAILABILITY A
WHERE S.id = A.student AND A.day is null
;

-- 2 lower
SELECT
  S.givenName,
  S.familyName
FROM Student S
WHERE S.id NOT IN
      (SELECT A.Student
       FROM Availability A)
;
-- 2 better
SELECT
  S.givenName, S.familyName
FROM Student S
LEFT JOIN Availability A ON A.Student = S.id
WHERE A.Student IS NULL
;

-- 3.
SELECT S.id,S.givenName,S.familyName
FROM STUDENT S,AVAILABILITY A
WHERE S.id = A.student AND A.day = 'Wednesday' AND A.hour = 10
;
-- 4.
SELECT S.givenName,S.familyName,G.name
FROM STUDENT S,STUDENTINGROUP SIG,GROUPS G
WHERE S.id = SIG.studentId AND SIG.groupId =G.id
;
-- 5.
SELECT G.id,G.name,COUNT(SIG.studentId)
FROM GROUPS G,STUDENTINGROUP SIP
WHERE G.id = SIP.groupId
GROUP BY SIG.groupId
HAVING COUNT(SIG.studentId) > 3
;
-- 6.
SELECT EXISTS
(SELECT 1
 FROM STUDENT S,AVAILABILITY A
 WHERE S.givenName = 'Alice' AND S.familyName = 'Smith' AND
       S.id = A.student AND A.day = 'Wed' AND A.hour = 12)
;

-- 6 modify
SELECT EXISTS
(
SELECT 1
FROM Student S, Availability A, Calendar C
WHERE S.id = A.Student AND A.day = C.day AND A.hour = C.hour
      AND C.description = 'lunch' AND S.givenName = 'Alice' AND S.familyName = 'Smith' AND a.day = 'Wdb'
)
;


-- 7.
SELECT A1.day,A1.hour
FROM STUDENT S1,STUDENT S2,AVAILABILITY A1,AVAILABILITY A2
WHERE S1.id = 10001 AND S2.id = 10002 AND S1.id = A1.student AND S2.id = A2.student
      AND A1.day = A2.day AND A1.hour = A2.hour
;
-- 8.
-- 8 列出组 id 和 name，学生的 familyName 是 按字母排序 (alphabetically) 在组中的

select c.name, c.givenName from (
select g.id, g.name, s.givenName
from Groups g, StudentInGroup sg, Student s
where g.id = sg.groupId and sg.StudentId = s.id
order by g.id, s.familyName desc
) c GROUP BY c.id
;


select
  b.id,
  SUBSTRING_INDEX(GROUP_CONCAT(b.givenName ),',',1) name
from
  (
    select g.id, g.name, s.givenName
    from Groups g, StudentInGroup sg, Student s
    where g.id = sg.groupId and sg.StudentId = s.id
    order by g.id, s.familyName desc
  ) b
GROUP BY id

;

select * from Student
;


select
  job_id,SUBSTRING_INDEX( GROUP_CONCAT(status order by start_time desc),',',1) status
from
  (
    select
      job_id,status,start_time
    from
      action_history
    where
      left(start_time,10) = CURDATE()
    order by job_id asc ,start_time desc
  )b
GROUP BY job_id
;

SELECT *
FROM (SELECT
        g.id,
        b.familyName,
        ROW_NUMBER() over(partition BY g.id ORDER BY g.ID, b.familyName) AS new_index
      FROM Groups g
        LEFT JOIN (SELECT
                     sg.groupId,
                     s.familyName
                   FROM StudentInGroup sg, Student s
                   WHERE s.id = sg.StudentId) b ON g.id = b.groupId
     ) t
WHERE t.new_index = 1
;

SELECT *
FROM (SELECT
        a.CompanyID,
        a.UserName,
        a.AddTime,
        a.JF,
        ROW_NUMBER() over(partition BY a.CompanyID ORDER BY b.ID) AS new_index
      FROM dbo.LB_Company a
        LEFT JOIN dbo.LB_Certificate b ON a.CompanyID = b.CompanyID
      WHERE a.CompanyID IN (361, 414, 447)
     ) t
WHERE t.new_index = 1
;



SELECT G.id, S.familyName
FROM GROUPS G, STUDENTINGROUP SIP,STUDENT S
WHERE G.id = SIP.groupId AND SIP.studentId = S.id
GROUP BY SIP.groupId
ORDER BY S.familyName
LIMIT 1
;
-- 9.
SELECT S.id,S.givenName,S.familyName
FROM STUDENT S,AVAILABILITY A
WHERE S.id = A.student AND
      (SELECT *
       FROM AVAILABILITY A2
       WHERE A2.student = A.student AND A2.day = 'Wednesday' AND A2.hour = 10)
      AND
      (SELECT *
       FROM AVAILABILITY A3
       WHERE A3.student = A.student AND A3.day = 'Wednesday' AND A3.hour = 11)
      AND
      (SELECT *
       FROM AVAILABILITY A4
       WHERE A4.student = A.student AND A4.day = 'Wednesday' AND A4.hour =12)
;
-- 10.
SELECT EXISTS
(SELECT 1
 FROM GROUPS G,STUDENTINGROUP SIP
 WHERE G.name = 'WeLoveDb' AND G.id = SIP.groupId AND NOT EXISTS
 (SELECT *
  FROM AVAILABILITY A
  WHERE A.student = SIP.studentId AND NOT EXISTS
  (SELECT *
   FROM AVAILABILITY A2
   WHERE A2.student = A.student AND A2.day = 'Wednesday' AND A2.hour = 10)))
;

-- 9
SELECT
  s.id,
  s.givenName,
  a.hour
FROM Student s, Availability a
WHERE s.id = a.Student
      AND a.day = 'Wed'
      AND a.hour >= 10 AND a.hour < 12

;
select * from Availability a where a.Student = '10001'
;

select * from Calendar c where c.description = 'lunch'
;

-- 9
SELECT
  s.id,
  s.givenName
FROM Student s
WHERE exists(select 1 from Availability a1 where a1.day = 'Wed' and a1.hour = 10 and s.id = a1.Student)
  and exists(select 1 from Availability a1 where a1.day = 'Wed' and a1.hour = 11 and s.id = a1.Student)
;
