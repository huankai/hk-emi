package com.hk.emi.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;
import com.hk.pms.core.servcie.SysPermissionService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;

import java.io.IOException;

/**
 * @author huangkai
 */
public class CityServiceTest extends BaseTest {

    @Autowired
    private CityService cityService;

    /**
     * Test method for
     * {@link com.hk.core.service.BaseService#saveOrUpdate(Persistable)
     *
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
        cityService.saveOrUpdate(china);
    }

    @Test
    public void importExcel() throws IOException {
        cityService.importExcel(ClassLoader.getSystemResourceAsStream("City.xlsx"));
    }

    /**
     * Test method for
     * {@link com.hk.core.service.BaseService#findOne(java.io.Serializable)}.
     */
    @Test
    public void testFindOnePK() {
        City city = cityService.findOne("402881e7634f6cf701634f6d429c001a");
        System.out.println(JsonUtils.toJSONStringExcludes(city, "parent"));
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
        cityService.delete("4028c081638a9ceb01638a9d08440015");
    }

    @Autowired
    private SysPermissionService permissionService;
}
