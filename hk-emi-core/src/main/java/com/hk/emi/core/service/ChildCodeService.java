/**
 *
 */
package com.hk.emi.core.service;

import com.hk.core.service.BaseService;
import com.hk.emi.core.domain.ChildCode;

import java.util.List;

/**
 * @author: kevin
 * @date 2018年1月24日下午1:44:33
 */
public interface ChildCodeService extends BaseService<ChildCode, String> {

    /**
     * 查询子字典
     *
     * @param baseCodeId baseCodeId
     * @return
     */
    default List<ChildCode> findByBaseCodeId(String baseCodeId) {
        return findByBaseCodeIngoreChildCodes(baseCodeId);
    }

    /**
     * 查询子字典，并忽略指定的Code
     *
     * @param baseCodeId baseCodeId
     * @param childCodes 要忽略的子节点名称
     * @return
     */
    List<ChildCode> findByBaseCodeIngoreChildCodes(String baseCodeId, String... childCodes);

}
