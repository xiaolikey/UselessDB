# UselessDB
Only study no any use database.

## Overview
* 当前系统本质是为了分享内容而写的一个毫无意义的数据库，这是个非常特殊的关系数据库（key-value数据库）。
* 这个数据库只有一个数据库，一个表，数据库数据本身存储在一个叫my.usb文件中。
* 这个表的每行记录record只有3个字段，id，key，value:

  | id (long) | key (varchar(128)) | value (varchar(1024)) |
  |-----------|--------------------|-----------------------|
  | 1         | SampleKey1         | SampleValue1          |
  | 2         | SampleKey2         | SampleValue2          |
  | ...       | ...                | ...                   |

* id为插入时系统自动生成64位自增长id，key是唯一键, key只能由数字和字母组成, value只能由数字字母+空格组成。
* 用户对于数据库只有7个操作, 通过cli进行交换:

i. 1个DML语句
```bash
# 插入数据,返回id
insert into ('[key]', '[value]') 
````

ii. 3个DQL语句
```bash
# 根据id查询数据,返回id,key,value
select from  where id=[id] 
# 根据key查询数据,返回id,key,value
select from  where key='[key]'
#根据word查询数据,返回第一条满足word包含在value里面的记录，id,key,value
select from  where value like '[word]' 
```
iii. 1个DDL语句
```bash
#清空全部数据,返回1
truncate 
```
vi. 2个系统执行
```bash
#退出系统
exit
````