
使用豆瓣Demo的框架进行开发

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