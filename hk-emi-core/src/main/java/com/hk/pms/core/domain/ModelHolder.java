package com.hk.pms.core.domain;

import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-04-12 11:25
 */
public class ModelHolder {


    @Data
    @MappedSuperclass
    public static class SysOrgBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_id")
        private SysOrg parent;

        @Column(name = "org_name")
        private String orgName;

        @Column(name = "description")
        private String description;

        @Column(name = "org_icon")
        private String orgIcon;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "responsible_id")
        private SysUser responsible;
    }

    @Data
    @MappedSuperclass
    public static class SysOrgDeptBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "org_id")
        private SysOrg org;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_id")
        private SysOrgDept parent;

        @Column(name = "dept_name")
        private String deptName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "responsible_id")
        private SysUser responsible;

        @Column(name = "description")
        private String description;

        /**
         * 部门角色
         */
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "sys_dept_role", joinColumns = {@JoinColumn(name = "dept_id")}, inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<SysRole> roleSet;
    }

    @Data
    @MappedSuperclass
    public static class SysPermissionBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "app_id")
        private SysApp app;

        @Column(name = "permission_code")
        private String permissionCode;

        @Column(name = "permission_name")
        private String permissionName;

        @Column(name = "description")
        private String description;
    }

    @Data
    @MappedSuperclass
    public static class SysUserRoleBase extends AbstractUUIDPersistable {

        @Column(name = "user_id")
        private String userId;

        @Column(name = "role_id")
        private String roleId;
    }


    @Data
    @MappedSuperclass
    public static class SysDeptRoleBase extends AbstractUUIDPersistable {

        @Column(name = "dept_id")
        private String deptId;

        @Column(name = "role_id")
        private String roleId;
    }

    @Data
    @MappedSuperclass
    public static class SysUserThirdBase extends AbstractAuditable {

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private SysUser user;

        @Column(name = "user_third_name")
        private String userThirdName;

        @Column(name = "open_id")
        private String openId;

        @Column(name = "icon_url")
        private String iconUrl;

        @Column(name = "account_type")
        private Byte accountType;
    }

    @Data
    @MappedSuperclass
    public static class SysRoleBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "app_id")
        private SysApp app;

        @Column(name = "role_name")
        private String roleName;

        @Column(name = "role_code")
        private String roleCode;

        @Column(name = "role_status")
        private Byte roleStatus;

        @Column(name = "description")
        private String description;

        /**
         * 角色权限
         */
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = @JoinColumn(name = "permission_id"))
        private Set<SysPermission> permissionSet;
    }

    @Data
    @MappedSuperclass
    public static class SysRolePermissionBase extends AbstractUUIDPersistable {

        @Column(name = "rolg_id")
        private String roleId;

        @Column(name = "permission_id")
        private String permissionId;

    }

    @Data
    @MappedSuperclass
    public static class SysUserBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "dept_id")
        private SysOrgDept orgDept;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "org_id")
        private SysOrg org;

        @Column(name = "phone")
        private String phone;

        @Column(name = "email")
        private String email;

        @Column(name = "real_name")
        private String realName;

        @Column(name = "password")
        private String password;

        @Column(name = "user_type")
        private Byte userType;

        @Column(name = "is_protect")
        private Boolean isProtect;

        @Column(name = "sex")
        private Byte sex;

        @Column(name = "icon_path")
        private String iconPath;

        @Column(name = "birth")
        private LocalDate brith;

        @Column(name = "user_status")
        private Byte userStatus;

        /**
         * 用户角色
         */
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<SysRole> roleSet;
    }


    @Data
    @MappedSuperclass
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
        private Byte appStatus;

    }
}
