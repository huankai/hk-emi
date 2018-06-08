package com.hk.emi.test;

import com.hk.core.data.commons.query.Order;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.service.BaseCodeService;
import org.apache.poi.ss.formula.functions.T;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-06-08 11:32
 */
public class BaseCodeTest extends BaseTest {

    @Autowired
    private BaseCodeService baseCodeService;

    /**
     * 保存或更新
     *
     * @param entity
     * @return
     */
    @Test
    public void saveOrUpdate() {
        BaseCode baseCode = new BaseCode();
        baseCode.setId("4028c08163dd77bf0163dd77d1550000");
        baseCode.setBaseCode("YES_NO_");
        baseCode.setCodeName("是否");
        baseCodeService.saveOrUpdate(baseCode);
    }

    /**
     * 批量保存或更新
     *
     * @param entities
     * @return
     */
    @Test
    public void batchSaveOrUpdate() {
        List<BaseCode> list = Lists.newArrayList();
        BaseCode code;
        for (int i = 0; i < 10; i++) {
            code = new BaseCode();
            code.setBaseCode("Item_" + i);
            code.setCodeName("Item_name" + i);
            list.add(code);
        }
        baseCodeService.saveOrUpdate(list);
    }

    /**
     * @param id
     * @return
     */
    public void findOne(String id) {

    }

    /**
     * @param id
     * @return
     */
    public void getOne(String id) {

    }

    /**
     * @param t
     * @return
     */
    public void findOne(T t) {

    }

    /**
     * @param t
     * @param orders
     * @param <S>
     * @return
     */
    public void findAll(T t, Order... orders) {

    }

    /**
     * @return
     */
    public void findAll() {

    }

    /**
     * @param ids
     * @return
     */
    public void findByIds(Iterable<String> ids) {

    }

    /**
     * 分页查询
     *
     * @param query 查询参数
     * @return 查询结果
     */
    public void queryForPage(QueryModel query) {

    }

    /**
     * @param id
     * @return
     */
    public void exists(String id) {

    }

    /**
     * @param t
     * @return
     */
    public void exists(T t) {

    }

    /**
     * @return
     */
    public void count() {

    }

    /**
     * @return
     */
    public void count(T t) {

    }

    /**
     * @param id
     */
    public void deleteById(String id) {

    }

    /**
     * @param entity
     */
    public void delete(T entity) {

    }

    /**
     * @param entities
     */
    public void delete(Iterable<? extends T> entities) {

    }
}
