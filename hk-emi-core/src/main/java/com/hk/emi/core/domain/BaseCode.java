package com.hk.emi.core.domain;

import com.hk.core.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 字典
 *
 * @author: huangkai
 * @date 2017-11-29 16:27
 */
@Data
@Entity
@Table(name = "sys_base_code")
@EqualsAndHashCode(callSuper = true)
public class BaseCode extends AbstractUUIDPersistable {

    /**
     *
     */
    @Column(name = "base_code")
    private String baseCode;

    /**
     *
     */
    @Column(name = "code_name")
    private String codeName;

    /**
     *
     */
    @Column(name = "description")
    private String description;

    /**
     * 使用级联删除
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "base_code_id", referencedColumnName = "id")
    private List<ChildCode> childCodes;

}
