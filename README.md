特性：

支持基于retrofit网络库的动态主机地址切换工具

支持管理多服务器环境

### 引入

```text
// 根build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}

// app的build.gradle
implementation "com.github.landscapeside:rehost:latest.release"

```

### 使用

- 在应用module（一般名字叫`app`）的`src/main/assets`目录下新建`hosts.json`文件，其内容格式如下：

```text
//每个配置项均由name标识配置名字以及urls包含主机地址数组，名字不可重复
[
  {
    "name": "dev",
    "urls": [
      "https://bpm.ijovo.com/",
      "http://jxbscbd.ijovo.com/"
    ]
  },
  {
    "name": "release",
    "urls": [
      "https://bpmts.ijovo.com/",
      "https://api.jxbscbd.com/"
    ]
  },
  {
    "name": "virtualServer",
    "urls": [
      "http://localhost:10000/",
      "http://localhost:10000/"
    ]
  }
]

```

- 在`Application`实现类的`onCreate`生命周期中初始化：

```text
// 得到一个未设置baseUrl的Retrofit.Builder，具体方式参考retrofit用法
// 用法一
ReHost.init(
        this,
        BuildConfig.DEBUG,
        retrofitBuilder
    ){switchRelease->
	if(switchRelease){
		// 切换到正式环境了，可以执行一些其他操作
	}else{
		.....
	}
}

// 用法二
ReHost.init(
        this,
        BuildConfig.DEBUG,
        retrofitBuilder
    )

```

- 通过某个按钮可以打开环境选择器：

```text
// 点击某个调试按钮后：
ReHost.openBoard(activity)
```

选择器如图：

![](https://tcs.teambition.net/storage/3126c256e3962a3f8e8024ba01ed191bb66b?Signature=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBJRCI6IjU5Mzc3MGZmODM5NjMyMDAyZTAzNThmMSIsIl9hcHBJZCI6IjU5Mzc3MGZmODM5NjMyMDAyZTAzNThmMSIsIl9vcmdhbml6YXRpb25JZCI6IiIsImV4cCI6MTYzNTg0MzA0MSwiaWF0IjoxNjM1MjM4MjQxLCJyZXNvdXJjZSI6Ii9zdG9yYWdlLzMxMjZjMjU2ZTM5NjJhM2Y4ZTgwMjRiYTAxZWQxOTFiYjY2YiJ9.cpQller5ru5drxTZaXnukxJ4BKzRPObiLo5fSgVekKE&download=image.png "")

勾选某个配置之后即可生效，关闭窗口即可，另可通过编辑和新增对配置进行管理

- 将retrofit.create方法调用替换为ReHost.**createApiX**调用，***X***支持**0-9**（即配置项中地址数量最多支持10个）

