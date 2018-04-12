package com.hk.emi.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author huangkai
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

    private ModelHolder() {

    }

    @Data
    @MappedSuperclass
    public static class BaseCodeBase extends AbstractUUIDPersistable {

        /**
         *
         */
        @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "base_code_id", referencedColumnName = "id")
        private List<ChildCode> childCodes;

        @Column(name = "base_code")
        private String baseCode;

        @Column(name = "code_name")
        private String codeName;

        @Column(name = "description")
        private String description;

    }

    @Data
    @MappedSuperclass
    public static class ChildCodeBase extends AbstractAuditable {

        @ManyToOne(optional = false)
        private BaseCode baseCode;

        @Column(name = "child_code")
        private String childCode;

        @Column(name = "code_name")
        private String codeName;

        @Column(name = "state")
        private Integer state;

        @Column(name = "description")
        private String description;

    }


    @Data
    @MappedSuperclass
    public static class CityBase extends AbstractAuditable {

        /**
         * 行政代码
         */
        @Column(name = "code")
        private String code;

        /**
         * 全称
         */
        @Column(name = "full_name")
        private String fullName;

        /**
         * 简称
         */
        @Column(name = "short_name")
        private String shortName;

        /**
         * 英文名
         */
        @Column(name = "english_name")
        private String englishName;

        /**
         * 邮编
         */
        @Column(name = "post_office")
        private String postOffice;

        /**
         * 描述
         */
        @Column(name = "description")
        private String description;

        /**
         * 上级
         */
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JSONField(serialize = false)
        private City parent;

        /**
         * 子级
         */
        @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
        @JoinColumn(name = "parent_id", referencedColumnName = "id")
        private List<City> childs;

    }

}
