/**
 * 
 */
package com.hk.emi.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.emi.EmiApplication;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;

/**
 * @author huangkai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { EmiApplication.class})
public class CityServiceTest {

	@Autowired
	private CityService cityService;
	
	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#saveOrUpdate(java.lang.Object)}.
	 * @throws IOException 
	 */
	@Test
	public void testSaveOrUpdate() throws IOException {
		City china = new City();
		china.setCode("1");
		china.setEnglishName("China");
		china.setFullName("中国");
		china.setShortName("中国");
		china.setPostOffice("1");
		City parent = cityService.saveOrUpdate(china);
		if(null != parent) {
			InputStream inputStream = CityServiceTest.class.getResourceAsStream("city.json");
			String jsonString = JSON.parseObject(inputStream, Charset.defaultCharset(), String.class);
			List<City> list = JsonUtils.parseObjectToList(jsonString, City.class);
			list.forEach(item -> item.setParent(parent));
			cityService.saveOrUpdate(list);
		}
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#findOne(java.io.Serializable)}.
	 */
	@Test
	@Transactional(readOnly = true)
	public void testFindOnePK() {
		City city = cityService.findOne("4028c081612643ff016126440caf0000");
		System.out.println(JsonUtils.toJSONString(city));
//		System.out.println(city.getFullName());
//		System.out.println(city.getParent());
//		List<City> childs = city.getChilds();
//		for (City child : childs) {
//			System.out.println(child.getFullName());
//		}
	}
	
	@Test
	public void testQueryForPage() {
		QueryModel model = new QueryModel();
		model.setPageIndex(2);
		model.setPageSize(10);
		model.setOrders(Lists.newArrayList(Order.asc("createdDate")));
		QueryPageable<City> page = cityService.queryForPage(model);
		System.out.println(page.getTotalRowCount());
		for (City city : page.getData()) {
			System.out.println(city.getFullName());
		}
	}
	
	@Test
	public void testJpaQueryForPage() {
		JpaQueryModel<City> model = new JpaQueryModel<>();
		model.setPageIndex(1);
		model.setPageSize(10);
		City city = new City();
//		city.setCode("110115");
		model.setParams(city);
		model.setOrders(Lists.newArrayList(Order.asc("createdDate")));
		QueryPageable<City> page = cityService.queryForPage(model);
		System.out.println(page.getTotalRowCount());
		for (City item : page.getData()) {
			System.out.println(item.getFullName());
		}
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#getOne(java.io.Serializable)}.
	 */
	@Test
	public void testGetOne() {
	}

	/**
	 * Test method for
	 * {@link com.hk.core.service.BaseService#delete(java.io.Serializable)}.
	 */
	@Test
	public void testDeletePK() {
		cityService.delete("402881e66092f9a9016092f9cd320000");
	}

}
