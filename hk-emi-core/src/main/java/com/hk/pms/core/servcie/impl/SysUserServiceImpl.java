package com.hk.pms.core.servcie.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.repository.SysUserRepository;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
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

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("password");
    }

    @Override
    protected <S extends SysUser> S checkNull(S t) {
        S s = super.checkNull(t);
        if (!getUserPrincipal().isAdministrator()) {
            s.setIsProtect(Boolean.FALSE);
        }
        return s;
    }

    @Override
    public SysUser disable(String userId) {
        return updateStatus(userId, ByteConstants.ZERO);
    }

    @Override
    public SysUser enable(String userId) {
        return updateStatus(userId, ByteConstants.ONE);
    }

    private SysUser updateStatus(String userId, Byte userStatus) {
        SysUser sysUser = getOne(userId);
        sysUser.setUserStatus(userStatus);
        return saveOrUpdate(sysUser);
    }

    @Override
    protected <S extends SysUser> S saveBefore(S entity) {
        if (null == entity.getUserStatus()) {
            entity.setUserStatus(ByteConstants.ONE);
        }
        if (null == entity.getIsProtect()) {
            entity.setIsProtect(Boolean.FALSE);
        }
        return super.saveBefore(entity);
    }

}
