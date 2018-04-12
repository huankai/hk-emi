package com.hk.pms.core.domain;

import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @author: huangkai
 * @date 2018-04-12 11:25
 */
public class ModelHolder {


    @MappedSuperclass
    @Data
    public static class SysOrgBase extends AbstractAuditable {

        @Column(name = "org_name")
        private String orgName;

        @Column(name = "description")
        private String description;

        @Column(name = "org_icon")
        private String orgIcon;

        private SysUser responsible;
    }

    @MappedSuperclass
    @Data
    public static class SysOrgDeptBase extends AbstractAuditable {

        @Column
        private SysOrg org;

        private String deptName;

        private SysUser responsible;

        private String description;
    }

    @MappedSuperclass
    @Data
    public static class SysPermissionBase extends AbstractAuditable {

        private SysApp app;

        private String permissionCode;

        private String permissionName;

        private String description;
    }

    @MappedSuperclass
    @Data
    public static class SysUserRoleBase extends AbstractUUIDPersistable {

        private String userId;

        private String roleId;
    }


    @MappedSuperclass
    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class SysDeptRoleBase extends AbstractUUIDPersistable {

        private String deptId;

        private String roleId;
    }

    @MappedSuperclass
    @Data
    public static class SysUserThirdBase extends AbstractAuditable {

        private SysUser user;

        @Column(name = "user_third_name")
        private String userThirdName;

        @Column(name = "icon_url")
        private String iconUrl;

        @Column(name = "account_type")
        private Integer accountType;
    }

    @MappedSuperclass
    @Data
    public static class SysRoleBase extends AbstractAuditable {

        private SysApp app;

        @Column(name = "role_name")
        private String roleName;

        @Column(name = "role_code")
        private String roleCode;

        @Column(name = "role_status")
        private Integer roleStatus;

        @Column(name = "description")
        private String description;
    }

    @MappedSuperclass
    @Data
    public static class SysRolePermissionBase extends AbstractUUIDPersistable {

        private String roleId;

        private String permissionId;

    }


    @MappedSuperclass
    @Data
    public static class SysUserBase extends AbstractAuditable {

        private SysOrgDept orgDept;

        private String phone;

        private String email;

        private String realName;

        private Boolean isProtect;

        private Integer sex;

        private String iconPath;

        private LocalDate brith;

        private Integer userStatus;


    }


    @MappedSuperclass
    @Data
    public static class SysAppBase extends AbstractAuditable {

        @Column(name = "app_name")
        private String appName;

        @Column(name = "app_code")
        private String appCode;

        @Column(name = "app_ip")
        private String appIp;

        @Column(name = "app_icon")
        private String appIcon;

        @Column(name = "app_port")
        private Integer appPort;

        @Column(name = "app_status")
        private Integer appStatus;

    }
}
