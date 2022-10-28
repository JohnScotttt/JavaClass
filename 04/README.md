# **！！！在运行项目前请务必仔细阅读本说明，否则可能无法正常运行！！！**

项目运行环境：**jdk17[^1]、MySQL8.0[^2]、VScode**

lib文件夹下的**mysql-connector-j-8.0.31.jar**属于依赖文件，**不可更改、删除**。

在运行项目前需要自行提前建立数据表，其字段按顺序依次为name(string)、addr(string)、sex(string)、age(int)、math(int)、eng(int)，可以按自己的方法创建表，或在mysql命令行中按以下代码操作。

（如果你完全没有基础，请暂时忽略这个，本文最后有完整的创建方法）

```mysql
create table stu(
    name varchar(20),
    addr varchar(20),
    sex varchar(1),
    age int,
    math int,
    eng int
);
```

创建完表后，修改App.java文件的第11行stu.load()的参数

```java
stu.load("demo", "demo", "demopw123456", "stu");
```

四个参数分别是数据库名(database)、用户名(user)、用户密码(password)、数据表名(table)，请务必按照自己电脑实际环境和设置更改相应的内容。

设置到这一步，理论上程序已经可以正常运行，如果你是使用**IntelliJ IDEA**或者**Eclipse**，你需要将lib文件夹下的**mysql-connector-j-8.0.31.jar**设为依赖（如图）![mysql-connector-j-8.0.31](https://github.com/JohnScotttt/ImageHosting/blob/main/JavaClass/04/mysql-connector-j-8.0.31.png)

## 0基础MySQL配置

1. 安装完MySQL后，找到MySQL Command Line Client启动

2. 输入安装时设置的root密码

   ![enter password](https://github.com/JohnScotttt/ImageHosting/blob/main/JavaClass/04/enter_pw.png)

3. 正常进入MySQL命令行应该是如下的样子，如果不是就是密码输错了

   ![mysql cmd](https://github.com/JohnScotttt/ImageHosting/blob/main/JavaClass/04/mysql_cmd.png)

4. 最后在MySQL命令行中输入以下代码即可

   ```mysql
   create database if not exists demo;
   use demo;
   create table if not exists stu(
       name varchar(20),
       addr varchar(20),
       sex varchar(1),
       age int,
       math int,
       eng int
   );
   ```

   在此情况下数据库名称(database)为demo，数据表名称(table)为stu，用户名(user)为root，用户密码(password)就是MySQL安装时设置的密码。

[^1]: jdk-17.0.3.1
[^2]: MySQL-8.0.30.0
