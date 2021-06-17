#### 配置使用

扫描包增加 ```com.ruitu.common.redis```

nacos config

需要配置文件
```yml
redis:
  host: 127.0.0.1
  port: 6379
  password: 123456
  minIdle: 10
  maxIdle: 300
  maxTotal: 800
  timeoutMS: 1000
  testOnBorrow: true
  database: 1
```