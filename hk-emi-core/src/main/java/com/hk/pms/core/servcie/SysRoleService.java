package com.hk.pms.core.servcie;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.service.BaseService;
import com.hk.pms.core.domain.SysRole;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-12 16:59
 */
public interface SysRoleService extends BaseService<SysRole, String> {


    /**
     * 获取指定用户的所有角色
     *
     * @param userId userId
     * @return
     */
    List<SysRole> getRoleList(String userId);

    /**
     * 获取当前登陆用户的所有角色
     *
     * @return
     */
    default List<SysRole> getCurrentUserRoleList() {
        return getRoleList(SecurityContextUtils.getSecurityContext().getPrincipal().getUserId());
    }

}
