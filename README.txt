基于淘宝diamond改造的配置管理服务器，主要做了如下改造
1：重新设计了界面，基于bootstrap样式
2：重新重构了工程的pom.xml,更新jar
3：去掉了不必要的功能代码
4：项目编码改成了UTF-8
5：数据库表config-info增加了字段username,用于控制登录用户只能操作自己的配置数据。

部署：
1：dimaond-server数据库脚本
CREATE TABLE `config_info` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL DEFAULT '',
  `group_id` varchar(128) NOT NULL DEFAULT '',
  `username` varchar(100) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `content` longtext NOT NULL,
  `md5` varchar(32) NOT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8

2：安装tomcat服务器，采用8080端口，因为diamond-client默认使用8080连接服务器端
3：dimaond-server构建获取war包（mvn clean install -Pproduction），修改war名称为diamond-server.war，
4：修改数据库连接，数据库配置文件为:META-INF/res/jdbc-production.properties
5：diamond-server访问用户数据存放在文件:/META-INF/res/user.properties中，可以在此文件中修改用户数据。
6：启动tomcat服务器。
7：启动服务器以后，diamond-server.war被解压出一个目录diamond-server，在diamond-server目录中创建文件dimaond
       文件添加内容为当前服务器IP地址

#### production

1. 数据库密码取自~/.druid
root=root

2.　请求的操作无法在使用用户映射区域打开的文件上执行
    <configuration>
      <webAppConfig>
      <defaultsDescriptor>src/test/resources/webdefault.xml</defaultsDescriptor>
     </webAppConfig>
     </configuration>
useFileMappedBuffer
true --> false


localFile    diamond/data/config-data/group/dataId
server
snapshot     diamond/snapshot

先从localFile里取,
mapfile.js ==> Map<String/* address */, Map<String/* dataId */, String/* group */>> localMap 若在这里面则
diamond/data/config-data/group/dataId ==> diamond/data/config-data/${group}/dataId

从local取的，此时在服务端更新客户端不会有更新。
checkDiamondServerConfigInfo -> getProbeUpdateString -> // 非使用本地配置，才去diamond server检查
                                                        				if (!data.isUseLocalConfigInfo()) {

                                                        				}
服务端的话就是采用session,
用户采用properties存储　
节点要通知，　node.properties
http获取md5每个节点上有一个cache（本地的)
http获取content每个节点上将db里数据写在webapp下然后直接文件返回了

上面这几个感觉都不太好，　可以改进一下。　


