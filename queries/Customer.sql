
-- find customer who had the most transactions
select c.id, count(*) 
from Customer c 
join Transaction t on t.customer_id = c.id 
group by c.id
order by count(*)
DESC limit 1;

-- find customer who spent the most money
select c.id, sum(totalprice) as tot 
from customer c join transaction trans on trans.customer_id = c.id
group by c.id
order by tot DESC 
limit 1;

-- find which state has the most customers
select n.state, max(n.state_cnt) mx from
    (select count(*) as state_cnt, c.state as state
        from customer as c
        group by c.state) as n 
group by n.state
order by mx desc
limit 1;


