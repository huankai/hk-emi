package com.hk.pms.core.servcie.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.core.exception.ServiceException;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.repository.SysUserRepository;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public <S extends SysUser> S saveOrUpdate(S entity) {
        defaultValue(entity);
        return super.saveOrUpdate(entity);
    }

    @Override
    public <S extends SysUser> List<S> saveOrUpdate(Iterable<S> entities) {
        entities.forEach(this::defaultValue);
        return super.saveOrUpdate(entities);
    }

    @Override
    public <S extends SysUser> S saveAndFlush(S entity) {
        defaultValue(entity);
        return super.saveAndFlush(entity);
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
    public void delete(String id) {
        SysUser sysUser = getOne(id);
        deleteBefore(sysUser);
        super.delete(id);
    }

    @Override
    public void delete(SysUser entity) {
        SysUser sysUser = findOne(entity);
        deleteBefore(sysUser);
        super.delete(sysUser.getId());
    }

    @Override
    public void delete(Iterable<? extends SysUser> entities) {
        entities.forEach(this::delete);
    }

    private void defaultValue(SysUser user) {
        if (null == user.getUserStatus()) {
            user.setUserStatus(ByteConstants.ONE);
        }
        if (null == user.getIsProtect()) {
            user.setIsProtect(Boolean.FALSE);
        }
    }

    private void deleteBefore(SysUser user) {
        if (user.getIsProtect()) {
            throw new ServiceException("系统账号不能删除.");
        }
    }
}
