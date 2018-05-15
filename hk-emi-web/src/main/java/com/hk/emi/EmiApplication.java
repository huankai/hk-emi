/**
 *
 */
package com.hk.emi;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.web.AppCodeUtils;
import com.hk.pms.core.domain.ModelHolder;
import com.hk.pms.core.domain.SysPermission;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.servcie.SysRoleService;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kally
 * @date 2018年1月24日上午11:39:55
 */
@ServletComponentScan(basePackages = {"com.hk.core"})
@SpringBootApplication(scanBasePackages = {"com.hk"})

@EnableJpaRepositories(basePackages = {"com.hk"})
@EntityScan(basePackages = {"com.hk"})

// @EnableScheduling
public class EmiApplication /* extends SpringBootServletInitializer */ {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EmiApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

//	 @Override
//	 protected SpringApplicationBuilder configure(SpringApplicationBuilder
//	 builder) {
//	 return builder.sources(EmiApplication.class).bannerMode(Mode.OFF);
//	 }

	/*   **************************Spring Security Config **********************************    */

    @Bean
    public AbstractUserDetailService userDetailService() {
        return new AbstractUserDetailService() {

            @Autowired
            private SysUserService sysUserService;

            @Autowired
            private SysRoleService roleService;

            @Override
            protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
                SysUser user = sysUserService.findByLoginUsername(username);
                if (null == user) {
                    throw new UsernameNotFoundException("用户名或密码不正确");
                }
                SecurityUserPrincipal principal = new SecurityUserPrincipal(user.getIsProtect(), user.getId(), user.getRealName(), user.getPassword(), user.getRealName(),
                        user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getUserStatus());

                principal.setAppId(AppCodeUtils.getCurrentAppId());

                List<SysRole> roleList = roleService.getRoleList(user.getId(), null);

                Map<String, Collection<String>> roleMap = Maps.newHashMap();
                Map<String, Collection<String>> permissionMap = Maps.newHashMap();
                Set<String> permissionSet = Sets.newHashSet();
                roleList.forEach(item -> {
                    String appId = item.getApp().getId();
                    roleMap.merge(appId, Sets.newHashSet(item.getRoleCode()), (t, u) -> {
                        t.add(item.getRoleCode());
                        return t;
                    });
                    Set<SysPermission> rolePermissionSet = item.getPermissionSet();
                    if (CollectionUtils.isNotEmpty(rolePermissionSet)) {
                        Set<String> permissions = rolePermissionSet.stream().map(ModelHolder.SysPermissionBase::getPermissionCode).collect(Collectors.toSet());
                        permissionMap.merge(appId, permissions, (t, u) -> {
                            t.addAll(permissions);
                            return t;
                        });
                    }
                });
                principal.setAppRoleSet(roleMap);
                principal.setAppPermissionSet(permissionMap);
                return principal;
            }
        };
    }



	/* *****************文件处理器 ************************************ */

//	/**
//	 * 文件上传基本路径
//	 */
//	 @Value("${fs.upload.basepath}")
//	 private String basePath;

//	/**
//	 * 本地文件处理器
//	 *
//	 * @return
//	 */
//	 @Bean
//	 public FileHandler fileHandler() {
//	 return new LocalFileHandler(basePath);
//	 }


//
//	 /* ***************** 定时任务，删除目录 ************************************ */
//
//	 /**
//	 * 每天早上3点执行
//	 */
//	 @Scheduled(cron = "0 0 3 * * ?")
//	 public void deleteDir() {
//	 LOGGER.info("开始删除文件或文件...");
//	 try {
//	 File file = new File(basePath);
//	 if (file.isDirectory()) {
//	 String[] list = file.list();
//	 for (String item : list) {
//	 file = new File(file, item);
//	 LOGGER.info("开始删除文件或文件目录 ： {},时间：{}", file.getPath(), LocalDateTime.now());
//	 FileUtils.deleteDirectory(file);
//	 }
//	 }
//	 } catch (IOException e) {
//	 e.printStackTrace();
//	 LOGGER.error("删除文件失败，文件目录 为 {},时间：{},错误原因：{}", basePath, LocalDateTime.now(),
//	 e.getMessage());
//	 }
//	 }


//	 /* *****************Shiro Config ************************************ */
//
//	 @Bean("jdbcRealm")
//	 public Realm realm() {
//	 JdbcRealm jdbcRealm = new JdbcRealm();
//	 jdbcRealm.setCachingEnabled(true);
//	 HashedCredentialsMatcher credentialsMatcher = new
//	 HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
//	 credentialsMatcher.setStoredCredentialsHexEncoded(false); //
//	 是否使用Hex，这里不使用，密码加密@see
//	 // com.hk.commons.shiro.CryptosUtils.asSha512HashToBase64(Object,
//	 // Object)
//	 jdbcRealm.setCredentialsMatcher(credentialsMatcher);
//	 return jdbcRealm;
//	 }
//
//	 @Bean
//	 public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//	 DefaultShiroFilterChainDefinition chainDefinition = new
//	 DefaultShiroFilterChainDefinition();
//	 chainDefinition.addPathDefinition("/**", "anon");
//	 return chainDefinition;
//	 }

}
