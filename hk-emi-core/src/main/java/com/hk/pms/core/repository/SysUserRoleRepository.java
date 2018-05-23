package com.hk.pms.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.pms.core.domain.SysUserRole;

/**
 * @author: huangkai
 * @date 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends StringRepository<SysUserRole> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
