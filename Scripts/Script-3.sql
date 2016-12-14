insert into pdtcode values 
('A001', '아메리카노'),
('A002', '카푸치노'),
('A003', '헤이즐넛'),
('A004', '에스프레소'),
('B001', '딸기쉐이크'),
('B002', '후르츠와인'),
('B003', '팥빙수'),
('B004', '아이스초고');

insert into pdtsale values
('A001', 4500,150,10),
('A002', 3800,166,15),
('B001', 5200,123,20),
('B002', 3400,145,11);

select c.cfCode, cfName , cfOne, cfSell, cfMargin, cfOne*cfSell as salePrise,
round(cfOne*cfSell/11, 0) as addTax, cfOne*cfSell-round(cfOne*cfSell/11,0) as supplyPrice,
round(((cfOne*cfSell-round(cfOne*cfSell/11,0))*(cfMargin/100)),0) as marginPrice
from pdtcode c , pdtsale s
where c.cfCode = s.cfCode
order by salePrise desc;
