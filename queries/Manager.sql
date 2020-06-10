-- Maggie Chang for Manager business role
-- June 7, 2020

-- 1. Find the location the manager oversees 
SELECT * 
FROM Location l INNER JOIN Manager m 
ON l.manager_id = m.id 
WHERE m.email = "jenny.potts@gmail.com";

-- 2. Find all employees the manager oversees 
SELECT * 
FROM Employee e INNER JOIN Location l 
ON e.fk_location = l.id INNER JOIN Manager m 
ON l.manager_id = m.id 
WHERE m.email="jenny.potts@gmail.com";

-- 3. Find all shipments verified by the manager
SELECT * 
FROM ShipmentOrder so INNER JOIN Location l 
ON so.fk_location = l.id INNER JOIN Manager m 
ON l.manager_id = m.id 
WHERE m.email="jenny.potts@gmail.com";
