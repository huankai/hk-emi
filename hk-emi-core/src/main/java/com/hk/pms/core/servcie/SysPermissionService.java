package com.hk.pms.core.servcie;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.service.BaseService;
import com.hk.pms.core.domain.SysPermission;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-12 16:53
 */
public interface SysPermissionService extends BaseService<SysPermission, String> {

    /**
     * 获取指定用户的所有角色
     *
     * @param userId userId
     * @return
     */
    List<SysPermission> getPermissionList(String userId);

    /**
     * 获取当前登陆用户的所有角色
     *
     * @return
     */
    default List<SysPermission> getCurrentUserPermissionList() {
        return getPermissionList(SecurityContextUtils.getSecurityContext().getPrincipal().getUserId());
    }


}
