/**
 * 
 */
package com.hk.emi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author kally
 * @date 2018年1月24日上午11:39:55
 */
@ServletComponentScan(basePackages = { "com.hk.core" })
@SpringBootApplication(scanBasePackages = {"com.hk"})
@EnableJpaRepositories(basePackages = {"com.hk"})
@EntityScan(basePackages = {"com.hk"})
// @EnableCaching
// @EnableScheduling
public class EmiApplication /* extends SpringBootServletInitializer */ {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(EmiApplication.class);

	/**
	 * 文件上传基本路径
	 */
	// @Value("${fs.upload.basepath}")
	// private String basePath;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication();
		application.setBannerMode(Banner.Mode.OFF);
		application.run(EmiApplication.class,args);
//		SpringApplication.run(EmiApplication.class, args);
	}

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// builder) {
	// return builder.sources(EmiApplication.class).bannerMode(Mode.OFF);
	// }

	/* *****************文件处理器 ************************************ */

//	/**
//	 * 本地文件处理器
//	 *
//	 * @return
//	 */
	// @Bean
	// public FileHandler fileHandler() {
	// return new LocalFileHandler(basePath);
	// }
	//
	// /* ***************** 定时任务，删除目录 ************************************ */
	//
	// /**
	// * 每天早上3点执行
	// */
	// @Scheduled(cron = "0 0 3 * * ?")
	// public void deleteDir() {
	// LOGGER.info("开始删除文件或文件...");
	// try {
	// File file = new File(basePath);
	// if (file.isDirectory()) {
	// String[] list = file.list();
	// for (String item : list) {
	// file = new File(file, item);
	// LOGGER.info("开始删除文件或文件目录 ： {},时间：{}", file.getPath(), LocalDateTime.now());
	// FileUtils.deleteDirectory(file);
	// }
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// LOGGER.error("删除文件失败，文件目录 为 {},时间：{},错误原因：{}", basePath, LocalDateTime.now(),
	// e.getMessage());
	// }
	// }

	// /* *****************Shiro Config ************************************ */
	//
	// @Bean("jdbcRealm")
	// public Realm realm() {
	// JdbcRealm jdbcRealm = new JdbcRealm();
	// jdbcRealm.setCachingEnabled(true);
	// HashedCredentialsMatcher credentialsMatcher = new
	// HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
	// credentialsMatcher.setStoredCredentialsHexEncoded(false); //
	// 是否使用Hex，这里不使用，密码加密@see
	// // com.hk.commons.shiro.CryptosUtils.asSha512HashToBase64(Object,
	// // Object)
	// jdbcRealm.setCredentialsMatcher(credentialsMatcher);
	// return jdbcRealm;
	// }
	//
	// @Bean
	// public ShiroFilterChainDefinition shiroFilterChainDefinition() {
	// DefaultShiroFilterChainDefinition chainDefinition = new
	// DefaultShiroFilterChainDefinition();
	// chainDefinition.addPathDefinition("/**", "anon");
	// return chainDefinition;
	// }

}
