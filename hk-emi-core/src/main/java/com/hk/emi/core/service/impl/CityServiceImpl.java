package com.hk.emi.core.service.impl;

import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadExcel;
import com.hk.commons.util.BeanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.data.commons.BaseDao;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.CityRepository;
import com.hk.emi.core.service.CityService;
import com.hk.emi.core.vo.CityExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author huangkai
 */
@Service
@CacheConfig(cacheNames = "City")
public class CityServiceImpl extends EnableCacheServiceImpl<City, String> implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected BaseDao<City, String> getBaseDao() {
        return cityRepository;
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
        ReadParam<CityExcelVo> readableParam = ReadParam.<CityExcelVo>builder()
                .beanClazz(CityExcelVo.class)
                .build();
        ReadableExcel<CityExcelVo> readableExcel = new SimpleSaxReadExcel<>(readableParam);
        ReadResult<CityExcelVo> result = readableExcel.read(in);
        if (!result.hasErrors()) {
            List<CityExcelVo> resultList = result.getAllSheetData();
            List<City> cityList = findAll();
            City city;
            for (CityExcelVo item : resultList) {
                city = new City();
                BeanUtils.copyProperties(item, city);
                city.setLastModifiedBy("4028c08163872c4e0163872c65e30000");
                city.setCreatedBy("4028c08163872c4e0163872c65e30000");
                city.setLastModifiedDate(LocalDateTime.now());
                city.setCreatedDate(LocalDateTime.now());
                if (StringUtils.isNotEmpty(item.getParentName())) {
                    Optional<City> cityOptional = cityList.stream().filter(c -> StringUtils.equals(c.getFullName(), item.getParentName())).findFirst();
                    if (cityOptional.isPresent()) {
                        City parent = cityOptional.get();
                        city.setParent(parent);
                        city.setCityType(Byte.valueOf(Integer.valueOf(parent.getCityType() + 1).toString()));
                    }

                }
                cityList.add(city);
            }
            getCurrentProxy().saveOrUpdate(cityList);
        }
    }

    @Override
    public byte[] exportExcelData(City city) {
//        List<CityExcelVo> cityList = cityRepository.findExportExcelData(city);
//        WriteParam<CityExcelVo> param = WriteParam.<CityExcelVo>builder()
//                .beanClazz(CityExcelVo.class)
//                .data(cityList)
//                .build();
//        WriteableExcel<CityExcelVo> writeableExcel = new XSSFWriteableExcel<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        writeableExcel.write(param, baos);
        return baos.toByteArray();
    }
}
