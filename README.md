1、数据字典模块

- 省市区字典

- 基础数据字典

2、完成功能：

- 使用Spring Security 完成登陆、权限认证(受保护的用户拥有所有权限)、退出

- 完成国际化支持

- Service添加缓存功能，可继承 EnableCacheServiceImpl ，并在子类上添加 @CacheConfig 注解。

- 完成Permission功能的 mockmvc测试. 请查看: com.hk.emi.test.controller.PermissionControllerTest