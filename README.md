# bank
学校安排的实训项目

#### 1.5
新增MD5工具类，对用户的密码加盐后进行加密处理

#### 1.6需求
在业务层和持久层中间建立工厂层
包名：top.luyuni.bank.factory
类名：
UserDaoFactory（采用单例和同步）
功能说明：
在业务层和持久层中间建立工厂层，完成业务层和持久层的动态装配，消除业务层和持久层的耦合性。
- 1.读取properties文件，取得持久层类信息
- 2.通过反射技术创建持久层对象
- 3.把持久层对象装配给业务层
- 4.在业务层的方法中可以调用持久层相应的方法
使用classInfo.properties 配置持久层
className=top.luyuni.bank.dao.UserDAOImpl

