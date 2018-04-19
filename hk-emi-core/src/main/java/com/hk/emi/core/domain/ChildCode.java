package com.hk.emi.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2017-11-29 17:11
 */
@Data
@Entity
@Table(name = "sys_child_code")
public class ChildCode extends ModelHolder.ChildCodeBase {

    /**
     *
     */
    private static final long serialVersionUID = -6562442354700499998L;


}
