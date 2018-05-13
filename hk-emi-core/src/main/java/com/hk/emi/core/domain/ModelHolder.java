package com.hk.emi.core.domain;

import com.hk.core.domain.AbstractAuditable;
import com.hk.core.domain.AbstractTreePersistable;
import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @EqualsAndHashCode(callSuper = true)
    public static class BaseCodeBase extends AbstractUUIDPersistable {

        /**
         * 使用级联删除
         */
        @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
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
    @EqualsAndHashCode(callSuper = true)
    public static class ChildCodeBase extends AbstractAuditable {

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        private BaseCode baseCode;

        @Column(name = "child_code")
        private String childCode;

        @Column(name = "code_name")
        private String codeName;

        @Column(name = "state")
        private Byte state;

        @Column(name = "description")
        private String description;

    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class CityBase extends AbstractTreePersistable<City> {

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
         * 简名
         */
        @Column(name = "short_name")
        private String shortName;

        /**
         * 英文名
         */
        @Column(name = "english_name")
        private String englishName;

        /**
         * <pre>
         * 城市类型:
         *     1:国家,
         *     2:省或直辖市,
         *     3:市,
         *     4:区或县,
         *     5:镇,
         *     6:村
         * </pre>
         * @see com.hk.emi.core.domain.City.CityType
         */
        @Column(name = "city_type")
        private Byte cityType;

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
    }

}
