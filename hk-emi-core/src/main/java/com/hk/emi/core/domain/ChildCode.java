package com.hk.emi.core.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author: huangkai
 * @date 2017-11-29 17:11
 */
@Data
@Entity
@Table(name = "sys_child_code")
@EqualsAndHashCode(callSuper = true)
public class ChildCode extends AbstractAuditable {

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
