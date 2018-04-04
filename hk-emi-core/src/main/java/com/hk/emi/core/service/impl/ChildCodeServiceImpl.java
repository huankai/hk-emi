/**
 *
 */
package com.hk.emi.core.service.impl;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.ChildCode;
import com.hk.emi.core.repository.ChildCodeRepostory;
import com.hk.emi.core.service.ChildCodeService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kally
 * @date 2018年1月24日下午1:44:55
 */
@Service
public class ChildCodeServiceImpl extends BaseServiceImpl<ChildCode, String> implements ChildCodeService {

    @Autowired
    private ChildCodeRepostory childCodeRepostory;

    @Override
    protected BaseRepository<ChildCode, String> getBaseRepository() {
        return childCodeRepostory;
    }

    /**
     * 查询子字典
     *
     * @param baseCodeId
     * @return
     */
    @Override
    public List<ChildCode> findByBaseCodeId(String baseCodeId) {
        return StringUtils.isEmpty(baseCodeId) ? Collections.emptyList() : childCodeRepostory.findByBaseCodeId(baseCodeId);
    }

    /**
     * 查询子字典，并忽略指定的Code
     *
     * @param baseCodeId
     * @param childCodes
     * @return
     */
    @Override
    public List<ChildCode> findByBaseCodeIngoreChildCodes(String baseCodeId, String... childCodes) {
        List<ChildCode> childCodeList = findByBaseCodeId(baseCodeId);
        if (ArrayUtils.isNotEmpty(childCodes)) {
            childCodeList = childCodeList.stream().filter(item -> ArrayUtils.noContains(childCodes, item)).collect(Collectors.toList());
        }
        return childCodeList;
    }
}
