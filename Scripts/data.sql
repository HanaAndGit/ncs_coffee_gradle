select user(), DATABASE ();

-- 제품
CREATE TABLE product (
		product_code CHAR(4) NOT NULL,
		product_name VARCHAR(20) NULL
);

-- 제품
ALTER TABLE product
	ADD CONSTRAINT PK_product -- 제품 기본키
	PRIMARY KEY (
		product_code -- 제품코드
	);

-- 판매현황
CREATE TABLE sale (
		no INT NOT NULL,
		product_code CHAR(4) NULL,
		price INT NOT NULL,
		sale_cnt INT NOT NULL,
		margin_rate INT NOT NULL
);

-- 판매현황
ALTER TABLE sale
	ADD CONSTRAINT PK_sale -- 판매현황 기본키
	PRIMARY KEY (
		no -- 번호
	);

-- 판매현황
ALTER TABLE sale
	ADD CONSTRAINT FK_product_TO_sale2 -- 제품 -> 판매현황2
	FOREIGN KEY (
		product_code -- 제품코드
	)
	REFERENCES product ( -- 제품
		product_code -- 제품코드
	);
	
alter table sale modify no int not null auto_increment;


select * from product;
select * from sale;

-- data

insert into product values ("A001", "아메리카노"),
			("A002", "카푸치노"),
			("A003", "헤이즐넛"),
			("A004", "에스프레소"),
			("B001", "딸기쉐이크"),
			("B002", "후르츠와인"),
			("B003", "팥빙수"),
			("B004", "아이스초코");
			
			
select (select count(*)+1 from sale where price > s.price as 순위) 
from sale s
order by 순위;

select (select count(*)+1 from sale where (select price*sale_cnt) > (select s.price*s.sale_cnt)) as 순위, 
	s.product_code as 제품코드, 
	(select p.product_name from product where product_code= s.product_code) as 제품명,
	s.price as 제품단가,
	s.sale_cnt as 판매수량,
	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
	(select (select s.price*s.sale_cnt)/11) as 부가세액,
	(select s.price*s.sale_cnt) as 판매금액,
	s.margin_rate as 마진율,
	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
	from sale s left join product p on s.product_code = p.product_code 
	order by 판매수량 desc;
	

select (select count(*)+1 from sale where margin_rate > s.margin_rate ) as 순위, 
	s.product_code as 제품코드, 
	(select p.product_name from product where product_code= s.product_code) as 제품명,
	s.price as 제품단가,
	s.sale_cnt as 판매수량,
	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
	(select (select s.price*s.sale_cnt)/11) as 부가세액,
	(select s.price*s.sale_cnt) as 판매금액,
	s.margin_rate as 마진율,
	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
	from sale s left join product p on s.product_code = p.product_code 
	order by 마진액 desc;
ifnull((select count(*)+1 from sale where sale_cnt > s.sale_cnt), "합계") as 순위
group by 순위 with rollup;


select * from sale;

select
	ifnull((select count(*)+1 as 순위 from sale where sale_cnt > s.sale_cnt), "합계") as 순위,
	s.product_code as 제품코드, 
	(select p.product_name from product where product_code= s.product_code) as 제품명,
	s.price as 제품단가,
	s.sale_cnt as 판매수량,
	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
	(select (select s.price*s.sale_cnt)/11) as 부가세액,
	(select s.price*s.sale_cnt) as 판매금액,
	s.margin_rate as 마진율,
	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
	from sale s left join product p on s.product_code = p.product_code order by 순위 desc
union
	select "합계", null,null,null,null,sum((select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))),sum((select (select s.price*s.sale_cnt)/11)),sum((select s.price*s.sale_cnt)),null,sum((select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate))
	from sale s left join product p on s.product_code = p.product_code limit 4;


drop procedure if exists proc_sum;
delimiter $$
	create procedure proc_sum()
	begin
	declare i int(11);
	declare j varchar(4);
	set i = (select count(*)+1 from sale);
			(select
			ifnull((select count(*)+1 as 순위 from sale where (select price*sale_cnt) > (select s.price*s.sale_cnt)), "합계") as 순위,
			s.product_code as 제품코드, 
			(select p.product_name from product where product_code= s.product_code) as 제품명,
			s.price as 제품단가,
			s.sale_cnt as 판매수량,
			(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
			(select (select s.price*s.sale_cnt)/11) as 부가세액,
			(select s.price*s.sale_cnt) as 판매금액,
			s.margin_rate as 마진율,
			(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
			from sale s left join product p on s.product_code = p.product_code )
		
		union
		
			(select "합계" as 순위 , null,null,null,null,sum((select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))),sum((select (select s.price*s.sale_cnt)/11)),sum((select s.price*s.sale_cnt)),null,sum((select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate))
			from sale s left join product p on s.product_code = p.product_code limit i )
			
			
			order by 순위 asc ;
		
	end $$
delimiter ;

call proc_sum();

sale s left join product p on s.product_code = p.product_code 
select * from sale s left join product p on s.product_code = p.product_code order by 순위;


drop procedure if exists proc_order_price;
delimiter $$
   create procedure proc_order_price()
   begin       
      select (select count(*)+1 from sale where sale_cnt > s.sale_cnt) as 순위, 
	s.product_code as 제품코드, 
	(select p.product_name from product where product_code= s.product_code) as 제품명,
	s.price as 제품단가,
	s.sale_cnt as 판매수량,
	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
	(select (select s.price*s.sale_cnt)/11) as 부가세액,
	(select s.price*s.sale_cnt) as 판매금액,
	s.margin_rate as 마진율,
	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
	from sale s left join product p on s.product_code = p.product_code 
	order by 판매금액 desc;
   end $$
delimiter ;

call proc_order_price();





drop procedure if exists proc_order_margin;
delimiter $$
   create procedure proc_order_margin()
   begin       
      select (select count(*)+1 from sale where (select (select (select price*sale_cnt)-(select (select price*sale_cnt)/11))*margin_rate) > (select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) ) as 순위, 
	s.product_code as 제품코드, 
	(select p.product_name from product where product_code= s.product_code) as 제품명,
	s.price as 제품단가,
	s.sale_cnt as 판매수량,
	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,
	(select (select s.price*s.sale_cnt)/11) as 부가세액,
	(select s.price*s.sale_cnt) as 판매금액,
	s.margin_rate as 마진율,
	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액
	from sale s left join product p on s.product_code = p.product_code 
	order by 마진액 desc;
   end $$
delimiter ;

call proc_order_margin();
