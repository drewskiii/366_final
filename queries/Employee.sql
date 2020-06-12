-- Andrew Puleo for Employee business role

-- 1. Find all transactions that an employee facilitated
SELECT *
FROM Transactions t
JOIN Employees e ON t.employee = e.id
WHERE e.firstName = "Johanna" AND e.lastName = "Smith";

-- 2. Find all employees of a position
SELECT *
FROM Employees e
JOIN Positions p ON e.position = p.id
WHERE p.title = "Barista";

-- 3. Find all employees at a specific location
SELECT *
FROM Employees e
JOIN Locations l ON e.location = l.id
WHERE l.address = "1 Grand Avenue";