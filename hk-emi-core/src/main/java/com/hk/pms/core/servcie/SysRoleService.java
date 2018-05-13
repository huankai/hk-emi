package com.hk.pms.core.servcie;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.service.BaseService;
import com.hk.pms.core.domain.ModelHolder;
import com.hk.pms.core.domain.SysRole;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: huangkai
 * @date 2018-04-12 16:59
 */
public interface SysRoleService extends BaseService<SysRole, String> {


    /**
     * 获取指定用户的所有角色
     *
     * @param userId userId
     * @param appId  appId
     * @return
     */
    List<SysRole> getRoleList(String userId, String appId);

    /**
     * 用户是否有指定的角色
     *
     * @param userId   用户id
     * @param appId    appId
     * @param roleCode roleCode
     * @return
     */
    default boolean hasRole(String userId, String appId, String roleCode) {
        return StringUtils.isNotBlank(roleCode) && getRoleListAsString(userId, appId).contains(roleCode);
    }

    /**
     * @param appId appId
     * @return
     */
    default List<String> getCurrentUserRoleListAsString(String appId) {
        return getRoleListAsString(SecurityContextUtils.getPrincipal().getUserId(), appId);
    }

    /**
     * @param userId 用户id
     * @param appId  appId
     * @return
     */
    default List<String> getRoleListAsString(String userId, String appId) {
        return getRoleList(userId, appId)
                .stream()
                .map(ModelHolder.SysRoleBase::getRoleCode)
                .collect(Collectors.toList());
    }

    /**
     * 获取当前登陆用户的所有角色
     *
     * @return
     */
    default List<SysRole> getCurrentUserRoleList(String appId) {
        return getRoleList(SecurityContextUtils.getPrincipal().getUserId(), appId);
    }

}
