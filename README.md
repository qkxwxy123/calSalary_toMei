calSalary_toMei
==================
a calculator to Mei's shop

实现若干接口
------------

### 登录接口 POST
#### url:"/login"    
#### 参数：
```
json
{
    "username": String,
    "password": String
}
```
#### 说明：
username: 用户名

password: 密码

**此处用户名为手机号**

#### 响应：
```
json
{
    "status": Int,
    "msg": String,
    "data": String
}
```
#### 说明：
status: 状态码，200表示成功，404表示找不到页面，500表示服务器错误

msg: 信息

data: 数据

### 注册接口 POST
#### url:"/register"
#### 参数：
```
json
{
    "username": String,
    "password": String,
}
```
#### 说明：
username: 用户名

password: 密码

**用户名为手机号，根据手机号注册，注册后密码以RSA加密保存在数据库中**

#### 响应：
```
json
{
    "status": Int,
    "msg": String,
    "data": String
}
```
#### 说明：
status: 状态码，200表示成功，404表示找不到页面，500表示服务器错误

msg: 信息

data: 数据

### 检查接口 POST
#### 检查用户是否存在
#### url: "/checkUser"
#### 参数：
```
json
{
    "username": String
}
```

#### 响应：
```
json
{
    "status": Int,
    "msg": String,
    "data": String
}
```

### 更改密码接口 POST
#### url:"/changePassword"
#### 参数：
```
json
{
    "username": String,
    ["password": String],
    "newPsw": String
}
```
#### 说明：
username: 用户名，用户手机号，**必填**

password: 原密码，可选参数

newPsw: 新密码，必选参数

#### 响应：
```
json
{
    "status": Int,
    "msg": String,
    "data": String
}
```
#### 说明：
status: 状态码，200表示成功，404表示找不到页面，500表示服务器错误

msg: 信息

data: 数据

### 获取工作时间 GET
#### url:"/getWorkTime"
#### 参数:
```
json
{
	"workerName": String,
	"sex": String,
	"month": String
}
```
#### 说明：
workerName: 员工姓名

sex: 性别，根据姓名和性别区分人

month: 查询月份

#### 响应：
```
json
{
	"status": Int,
	"msg": String,
	"data":
	{
		"hours": String
	}
}
```
#### 说明：
hours: 工作时长

### 查询员工信息 GET
#### url:"/getWorker"
#### 参数：
```
json
{
	"name": String,
	"month": String
}
```

#### 响应：
```
json
{
	"status": Int,
	"msg": String,
	"data":
	{
		"name": String,
		"sex": String,
		"phone": String,
		"hours": float
	}
}
```
#### 说明：
hours: 工作时长

### 添加员工信息 POST
#### url:"/addWorker"
#### 参数：
```
json
{
	"name": String,
	"sex": String,
	"phone": String
}
```

#### 响应：
```
json
{
	"status": Int,
	"msg": String,
	"data": String
}
```

### 添加工作时长 POST
#### url: "/addWorkTime"
#### 参数：
```
json
{
	"name": String,
	"sex": String,
	"month": String,
	"time": float
}
```
#### 说明：
month: 当前月份

time: 工作时长

####响应：
```
json
{
	"status": Int,
	"msg": String,
	"data": String
}
```
