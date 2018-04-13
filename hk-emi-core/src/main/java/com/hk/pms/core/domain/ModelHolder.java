package com.hk.pms.core.domain;

import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author: huangkai
 * @date 2018-04-12 11:25
 */
public class ModelHolder {


    @MappedSuperclass
    @Data
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

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "responsible_id")
        private SysUser responsible;
    }

    @MappedSuperclass
    @Data
    public static class SysOrgDeptBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "org_id")
        private SysOrg org;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_id")
        private SysOrgDept parent;

        @Column(name = "dept_name")
        private String deptName;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "responsible_id")
        private SysUser responsible;

        @Column(name = "description")
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

        @Column(name = "is_protect")
        private Boolean isProtect;

        @Column(name = "sex")
        private Integer sex;

        @Column(name = "icon_path")
        private String iconPath;

        @Column(name = "birth")
        private LocalDate brith;

        @Column(name = "user_status")
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
