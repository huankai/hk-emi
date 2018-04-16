/**
 *
 */
package com.hk.emi.core.service.impl;

import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.ReadableParam;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.BeanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.CityRepository;
import com.hk.emi.core.service.CityService;
import com.hk.emi.core.vo.CityExcelVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author huangkai
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City, String> implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected BaseRepository<City, String> getBaseRepository() {
        return cityRepository;
    }

    @Override
    protected Example<City> getExample(City t) {
        if (null == t) {
            t = new City();
        }
        return Example.of(t, ExampleMatcher.matching()
                .withMatcher("code", GenericPropertyMatchers.exact())
                .withIgnoreNullValues());
    }

    /**
     * 查询下级City
     *
     * @param parentId
     * @return
     */
    @Override
    public List<City> findChildList(String parentId) {
        return StringUtils.isEmpty(parentId) ? Collections.emptyList() : cityRepository.findByParentId(parentId);
    }

    /**
     * 导入
     *
     * @param in excel文件
     */
    @Override
    @Transactional
    public void importExcel(InputStream in) {
        ReadableParam<CityExcelVo> readableParam = new ReadableParam<>();
        readableParam.setBeanClazz(CityExcelVo.class);
        ReadableExcel<CityExcelVo> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
        ReadResult<CityExcelVo> result = readableExcel.read(in);
        if (!result.hasErrors()) {
            List<CityExcelVo> resultList = result.getAllSheetData();
            List<City> cityList = findAll();
            resultList.forEach(item -> {
                City city = new City();
                BeanUtils.copyProperties(item, city);
                city.setCreatedDate(DateTime.now());
                city.setCreatedBy("4028c08162bda8ce0162bda8df6a0000");
                city.setLastModifiedDate(DateTime.now());
                city.setLastModifiedBy("4028c08162bda8ce0162bda8df6a0000");
                if (StringUtils.isNotEmpty(item.getParentName())) {
                    Optional<City> cityOptional = cityList.stream().filter(c -> StringUtils.equals(c.getFullName(), item.getParentName())).findFirst();
                    if (cityOptional.isPresent()) {
                        city.setParent(cityOptional.get());
                    }
                }
                cityList.add(city);
            });
            saveOrUpdate(cityList);
        }
    }

    @Override
    public byte[] exportExcelData(City city) {
        List<CityExcelVo> cityList = cityRepository.findExportExcelData(city);
        WriteParam<CityExcelVo> param = new WriteParam<>();
        param.setBeanClazz(CityExcelVo.class);
        param.setData(cityList);
        WriteableExcel<CityExcelVo> writeableExcel = new XSSFWriteableExcel<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeableExcel.write(param, baos);
        return baos.toByteArray();
    }
}
