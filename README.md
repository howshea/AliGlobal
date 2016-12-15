## 模仿淘宝国际版客户端
这是之前模仿Aliexpress(好像是这个名字)做的淘宝客户端  
用`tomcat`搭建的本地服务器，`MySql`做数据库，`Hibernate`读取和处理数据库内容，`Servlet`打包**json**数据给Android客户端

## 功能
- 注册、登录
- 查看用户详情
- 查看商品分类
- 查看商品详情
- 加入购物车
- 修改购物车
- 立即购买
- 购物车结算
- 查看历史订单

## 技术点
- 服务器端
  + Hibernate
  + Servlet
  + MySql
- Android端
  + 服务器请求用的是Okhttp3，自己封装了异步请求（做这个项目的时候Rxjava还没流行起来）
  + 图片异步加载用的是Picasso
  + Gson解析服务端传过来的数据
  + 用了很多Material Design的控件：
    * CardView
    * Toolbar
    * ViewPager
    * NestedScrollView
    * CollapsingToolbarLayout
    * 等等等等

## 数据库ermaster图
![](https://github.com/howshea/AliGlobal/raw/master/pictures/sql.png)

## 运行截图
<img src="https://github.com/howshea/AliGlobal/raw/master/pictures/1.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/2.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/3.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/4.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/5.png" width=200> 

<img src="https://github.com/howshea/AliGlobal/raw/master/pictures/6.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/7.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/8.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/9.png" width=200>  <img src="https://github.com/howshea/AliGlobal/raw/master/pictures/10.png" width=200> 

