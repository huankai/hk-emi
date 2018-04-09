package com.hk.emi.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.emi.core.domain.ChildCode;

import java.util.List;

/**
 * 
 * @author huangkai
 * @date 2017年12月1日下午2:20:44
 */
public interface ChildCodeRepostory extends StringRepository<ChildCode> {

    List<ChildCode> findByBaseCodeId(String baseCodeId);
}
