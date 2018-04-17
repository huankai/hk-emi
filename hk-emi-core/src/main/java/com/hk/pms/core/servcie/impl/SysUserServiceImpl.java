package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.repository.SysUserRepository;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 17:01
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;


    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysUser, String> getBaseRepository() {
        return sysUserRepository;
    }

    @Override
    public SysUser findByLoginUsername(String username) {
        return sysUserRepository.findByUserName(username);
    }
}
