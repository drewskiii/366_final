-- Find all the locations the supplier (ex. id 4) needs to ship to
SELECT *
FROM Location l
JOIN Supplier s ON l.id = s.location     -- But there are multiple locations so how does this work?
WHERE s.id = 4;

-- Get the price of each product (that gets shipped from a certain supplier?)
SELECT DISTINCT p.id, p.name, p.price
FROM Supplier s
JOIN ShipmentOrder so ON s.id = so.supplier
JOIN Product p ON p.id = so.product
WHERE s.id = 4;

-- Find the manager of each location things need to be shipped to
SELECT m.id, l.id
FROM Location l
JOIN Supplier s ON l.id = s.location     -- Same problem as first query
JOIN Manager m ON l.manager = m.id
WHERE s.id = 4;

-- Find all shipments to a certain location
SELECT *
FROM ShipmentOrder so
JOIN Location l ON so.location = l.id
WHERE l.id = 4;