# MailGroup

一个基于java spring、mybatis、mysql和java mail的邮件群发软件

## 注意事项
* 将mail.properties的用户名和密码改为使用者自己的参数，某些邮箱如qq邮箱需要使用者申请smtp授权码，然后需要修改host和port。
* 将jdbc.properties的用户名密码改为使用者自己的数据库用户名和密码。

## 数据库设计

Table user

|id             |name           |email  |
|---------------|---------------|-------|

