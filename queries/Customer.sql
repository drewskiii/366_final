
-- find customer who had the most transactions
select * from 
    (select c.id, count(*) as cnt
    from Customer c 
    join Transaction t on t.customer_id = c.id 
    group by c.id
    order by count(*)) as tbl1
where cnt = 
    (select cntt from 
        (select c.id, count(*) cntt
        from Customer c 
        join Transaction t on t.customer_id = c.id 
        group by c.id
        order by count(*) DESC
        limit 1)
    )

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


-- Find the customer(s) who spent the most on coffee between January 2020 and June 2020
select c.id, sum(t.totalprice) as tot
from 
    (select *
    from customers c
    join Transction t on c.id = t.customer
    where t.product = "Coffee" and t.date between "2020-01-01" and "2020-06-01"
    ) as tbl1
group by c.id
order by tot DESC
limit 1;

