

## 2016.10.25

* 修改mMenuType的对应值

	* -1 ：去掉AppTitleView下面的SelectionHolder
	* 0：首页-二手房Menu
	* 1：首页-租房Menu
	* 2：首页-客源Menu(购房)
	* 3：首页-客源Menu(租房)
	
	* 4：首页-最新成交Menu
	* 5：我-收藏房源-Item
	* 6：我-收藏客源-Item


## 2016.10.24
使用豆瓣Demo的框架进行开发


* 签名打包的时候出现：

	- http://stackoverflow.com/questions/34031395/errorexecution-failed-for-task-apptransformresourceswithmergejavaresfordebug
	- http://blog.csdn.net/lvshuchangyin/article/details/51803154
	- http://blog.csdn.net/github_14899071/article/details/51280390

	* 打包前 13.5MB，使用反编译工具之后可以看到里面的内容
	* 混淆之后 12.4MB 反编译之后，activity的东西是看得到的，其他内容被混淆了

参数说明：

* mMenuType
	* -1 ：去掉AppTitleView下面的SelectionHolder
	* 0：首页-二手房Menu
	* 1：首页-租房Menu
	* 2：首页-客源Menu
	* 3：首页-最新成交Menu
	* 4：我-收藏房源-Item
	* 5：我-收藏客源-Item

* 考虑是否可以对mMenuType细分

	* -1 ：去掉AppTitleView下面的SelectionHolder
	* 0：首页-二手房Menu
	* 1：首页-租房Menu
	* 2：首页-客源Menu
		* 是不是可以这样设置
			* 2  ---> 客源(购房)
			* -2 ---> 客源(租房)
	* 3：首页-最新成交Menu
	* 4：我-收藏房源-Item
	* 5：我-收藏客源-Item


* DataAnalysisActivity
	* mDataType;//数据分析的类型