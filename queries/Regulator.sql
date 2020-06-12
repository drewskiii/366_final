-- Zachary Liu for Owner / Board of Directors business rules
-- June 3, 2020

-- Find and rank the sum of revenue for each month
WITH transactionsPerMonth AS (
	SELECT SUM(t.totalPrice) AS monthRevenue, MONTH(t.date) AS mon
	FROM Transactions t
	GROUP BY mon
	ORDER BY monthProfit DESC
)
SELECT RANK() OVER (ORDER BY monthRevenue DESC) AS "Rank", t.monthRevenue, t.mon as "Month"
FROM transactionsPerMonth t

-- Find and rank the sum of liabilities for each month
-- assumption that Employee.hours is the hours per week and workers work the same amount of hours each week
-- with our current implementation our liabilities are the same each month
-- we do not have a history of previous employees
-- we cannot distinguish which employees are getting paid each month
-- we can only find the sum of liabilities
WITH employeePay AS (
	SELECT e.id, p.payRate, e.hours, (p.payRate * e.hours * 4) as totalPay
	FROM Employee e join Position p on e.position = p.id
)
SELECT SUM(e.totalPay) as totalLiabilities
FROM employeePay e

-- Because liabilities are always the same the next two queries are determined by revenues only.

-- Determine the most profitable month (revenue)
WITH transactionsPerMonth AS (
	SELECT SUM(t.totalPrice) AS monthRevenue, MONTH(t.date) AS mon
	FROM Transactions t
	GROUP BY mon
	ORDER BY monthRevenue DESC
), employeePay AS (
	SELECT e.id, p.payRate, e.hours, (p.payRate * e.hours * 4) as totalPay
	FROM Employee e join Position p on e.position = p.id
), maxMonth AS (
	SELECT MAX(t.monthRevenue) as totalRevenue, t.mon
	FROM transactionsPerMonth t
), totalEmployeePay AS (
	SELECT SUM(e.totalPay) as totalLiabilities
	FROM employeePay e
)
SELECT m.mon as "Month", (m.totalRevenue - t.totalLiabilities) as totalProfit
FROM maxMonth m join totalEmployeePay t


-- Determine the least profitable month (revenue)
WITH transactionsPerMonth AS (
	SELECT SUM(t.totalPrice) AS monthRevenue, MONTH(t.date) AS mon
	FROM Transactions t
	GROUP BY mon
	ORDER BY monthRevenue DESC
), employeePay AS (
	SELECT e.id, p.payRate, e.hours, (p.payRate * e.hours * 4) as totalPay
	FROM Employee e join Position p on e.position = p.id
), minMonth AS (
	SELECT MIN(t.monthRevenue) as totalRevenue, t.mon
	FROM transactionsPerMonth t
), totalEmployeePay AS (
	SELECT SUM(e.totalPay) as totalLiabilities
	FROM employeePay e
)
SELECT m.mon as "Month", (m.totalRevenue - t.totalLiabilities) as totalProfit
FROM minMonth m join totalEmployeePay t
