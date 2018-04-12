package com.hk.pms.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.pms.core.domain.SysApp;

/**
 * @author: huangkai
 * @date 2018-04-12 11:34
 */
public interface SysAppRepository extends StringRepository<SysApp> {

    SysApp findByAppCode(String appCode);
}
