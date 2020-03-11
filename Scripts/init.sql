select user(), DATABASE ();

DROP SCHEMA IF EXISTS ncs_coffee_gradle;

-- 내 스키마
CREATE SCHEMA ncs_coffee_gradle;

-- 제품
CREATE TABLE "product" (
		"product_code" CHAR(4) NOT NULL,
		"product_name" VARCHAR(20) NULL
);

-- 제품
ALTER TABLE "product"
	ADD CONSTRAINT "PK_product" -- 제품 기본키
	PRIMARY KEY (
		"product_code" -- 제품코드
	);

-- 판매현황
CREATE TABLE "sale" (
		"no" INT NOT NULL,
		"product_code" CHAR(4) NULL,
		"price" INT NOT NULL,
		"sale_cnt" INT NOT NULL,
		"margin_rate" INT NOT NULL
);

-- 판매현황
ALTER TABLE "sale"
	ADD CONSTRAINT "PK_sale" -- 판매현황 기본키
	PRIMARY KEY (
		"no" -- 번호
	);

-- 판매현황
ALTER TABLE "sale"
	ADD CONSTRAINT "FK_product_TO_sale2" -- 제품 -> 판매현황2
	FOREIGN KEY (
		"product_code" -- 제품코드
	)
	REFERENCES "product" ( -- 제품
		"product_code" -- 제품코드
	);