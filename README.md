### BicycleRent  

### 8014Coursework的project，push上来当作备份防止丢失

---
自行车租赁公司  
下属50公路自行车	10电动自行车  
电动自行车只能由Gold Class Customer租出  

---
一辆车同时只能被一个人租走，直至归还  
自行车的状态只有两种，租用中和闲置中  
一个人只能租一辆车  

---
租赁公司需要随时查询自行车是否被租走，以及租用当前自行车的客户信息  
在自行车归还时自动终止合同  

---
availableBikes(typeOfBike)  
此方法返回当前可租用的自行车数量  

---
getRentedBikes()    
此方法返回当前出租的所有自行车的集合（如果有的话）  

---
getBike(customerRecord)  
给定一个客户的记录，返回当前他正在租用的自行车编号  

---
issueBike(customerRecord, typeOfBike)  
给定一个客户的记录和自行车类型，返回此人是否能够租用该类型的自行车  
此方法将自行车和客户号码关联  
公司有正在租用的自行车和租用自行车的客户的记录  
如果自行车不能出租，返回无法租车  

---
terminateRental(customerRecord)
终止租赁，将租赁信息从记录上移除，此方法将归还的自行车状态设置为不出租，如果是电动自行车，则需要充电  

---
出租自行车需要遵循以下原则  
1. 租车人必须拥有客户记录  
2. 一个人不能再同一时间租用多辆自行车  
3. 租用公路自行车只需要有客户记录  
4. 租用电动自行车需要满21岁并且是金卡会员  

---
类的设计  
Bikes需要拥有以下的公共方法  
1. 一种获取自行车唯一序列号的方法  
2. 查询电动自行车的电量是否是满的。不需要考虑电量减少的方式  
3. 将电动自行车的电量设置为充满状态  
4. 查询自行车是否被租用  

---
BicycleSerialNumber  
自行车的编号由2个字母后跟随3个数字构成  
例如ab123  
需要保证没有相同的两个序列号  

---
CustomerRecord  
客户记录包括  
1. 姓氏和名字  
2. 唯一的客户编号  
3. 客户的出生日期  
4. 客户信息的注册年份  
5. 客户是否是金卡会员  

---
其中客户编号包含三个部分  
第一部分由客户名字的首字母与姓氏的首字母组成  
第二部分是记录发布的年份。  
第三部分是任意序列号。  
* 需要保证客户编号的唯一性  
* 使用java.util.Date类构造生日和客户记录发放日期  

