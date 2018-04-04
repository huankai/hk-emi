package com.hk.emi.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.emi.core.domain.ChildCode;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author huangkai
 * @date 2017年12月1日下午2:20:44
 */
public interface ChildCodeRepostory extends StringRepository<ChildCode> {

    @Query(value = "SELECT * FROM ChildCode WHERE base_code_id = ?1", nativeQuery = true)
    List<ChildCode> findByBaseCodeId(String baseCodeId);
}
