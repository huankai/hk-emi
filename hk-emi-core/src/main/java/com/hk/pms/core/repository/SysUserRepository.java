package com.hk.pms.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.pms.core.domain.SysUser;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: huangkai
 * @date 2018-04-12 16:42
 */
public interface SysUserRepository extends StringRepository<SysUser> {

    @Query(value = "select id,org_id,dept_id,phone,password,email,real_name,user_type,is_protect,sex,icon_path,birth,privince_id,city_id,user_status,created_by,created_date,last_modified_by,last_modified_date from sys_user where phone = ?1 or email = ?1", nativeQuery = true)
    SysUser findByUserName(String username);
}
