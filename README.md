# apirouter
integrate different apis

当前API接入格式：
===

##weather：

    1. /weather/51wnl/q?city=杭州    51万年历提供的天气查询接口
    2. /weather/zgtqw/zs/q?city=杭州    中国天气网提供的天气指数查询接口
    3. /weather/zgtqw/sk/q?city=杭州    中国天气网提供的天气实况查询接口

##train：
    1. /train/juhe/* 
暂时直接使用juhe, url后缀直接转发（ http://www.juhe.cn/docs/api/id/22/aid/284 ）
    
    2. /train/tieyou/s2s/q?from=杭州&to=北京&date=20150131 
根据起点、终点及日期查询火车

    3. /train/tieyou/checi/q?date=20150131&checi=K982 
根据日期及车次号查询详细信息

##flight：

    1. /flight/q?date=2015-01-13&dep=杭州&arr=北京
根据起点、终点及日期查询航班
        
    2. /flight/q?flightno=CA1703&date=2015-01-13
根据日期及航班号查询详细信息

