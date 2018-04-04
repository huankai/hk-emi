/**
 *
 */
package com.hk.emi.core.service.impl;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.ReadableParam;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadableExcel;
import com.hk.commons.poi.excel.read.validation.JSRValidation;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.StringUtils;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.CityRepository;
import com.hk.emi.core.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<ErrorLog<City>> importExcelData(InputStream excelInput) {
        ReadableParam<City> readableParam = new ReadableParam<>();
        readableParam.setBeanClazz(City.class);
        readableParam.setValidationList(Lists.newArrayList(new JSRValidation<>()));
        ReadableExcel<City> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
        ReadResult<City> readResult = readableExcel.read(excelInput);
        if (readResult.hasErrors()) {
            return readResult.getErrorLogList();
        }
        saveOrUpdate(readResult.getAllSheetData());
        return null;
    }

    @Override
    public byte[] getExcelData(City city) {
        List<City> cityList = findAll(city);
        WriteParam<City> writeParam = new WriteParam<>();
        writeParam.setData(cityList);
        writeParam.setBeanClazz(City.class);
        WriteableExcel<City> writeableExcel = new XSSFWriteableExcel<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeableExcel.write(writeParam, baos);
        return baos.toByteArray();
    }
}
