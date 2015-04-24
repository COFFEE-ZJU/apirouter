当前API接入格式：
===

##weather：

	请求格式： GET /weather?city=杭州
	
    返回格式(Sample):
	{
		"city": "杭州",
		"date": "2015年04月24日",
		"tempMin": "13℃",		//今日天气
		"tempMax": "24℃",
		"weatherInfo": "阴转多云",
		"tempMin2": "13℃",		//明日天气
		"tempMax2": "27℃",
		"weatherInfo2": "晴转多云",
		"tempMin3": "15℃",		//后天天气
		"tempMax3": "27℃",
		"weatherInfo3": "多云转阴"
	}

##train：

	1. 站到站查询(根据起点、终点及日期查询火车)
	
	请求格式： GET  /train/s2s/q?from=杭州&to=北京&date=20150131
	
    返回格式(Sample):
	{
		"data":[
			{
				"trainNo": "5600000G3230",
				"trainNumber": "G32",
				"fromStation": "杭州东",
				"toStation": "北京南",
				"fromStationType": "起点",
				"toStationType": "终点",
				"fromTime": "08:30",
				"toTime": "13:28",
				"dayDifference": 0,
				"durationTime": "04:58",
				"trainType": "高铁",
				"saleTime": "11:30",
				"controlDay": 59,
				"seats": [
					{
						"price": 538.5,
						"name": "二等座",
						"bookable": true,
						"restTickets": 94
					},
					{
						"price": 907,
						"name": "一等座",
						"bookable": true,
						"restTickets": 114
					},
					{
						"price": 1701,
						"name": "商务座",
						"bookable": true,
						"restTickets": 18
					}
				]
			},
			...
		]
	}
    

    2. 车次查询(根据日期及车次号查询详细信息)



