-- Grant Matejka for Owner / Board of Directors business rules
-- June 2, 2020

-- 1. Total number of receipts over all locations in the past year.
SELECT COUNT(t.id) AS TotalReceiptsPastYear
FROM Locations l
JOIN Transactions t ON t.location = l.id 
   and YEAR(t.date) >= YEAR(CURDATE()) - 1

-- 2. The profits of each location over the last month.
SELECT l.id as Location, SUM(t.totalPrice) AS ProfitsLastMonth
FROM Locations l
JOIN Transactions t ON t.location = l.id
   and MONTH(t.date) >= MONTH(CURDATE()) - 1
GROUP BY l.id

-- 3. The average number of employees each manager oversees.
SELECT COUNT(e.id)/COUNT(m.id) AS AvgOverseenEmployee
FROM Locations l
JOIN Managers m ON l.manager = m.id
JOIN Employees e ON e.location = l.id
