package com.hk.pms.core.servcie;

import com.hk.core.service.BaseService;
import com.hk.pms.core.domain.SysDeptRole;

/**
 * @author: huangkai
 * @date 2018-04-12 16:48
 */
public interface SysDeptRoleService extends BaseService<SysDeptRole,String> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);
}
