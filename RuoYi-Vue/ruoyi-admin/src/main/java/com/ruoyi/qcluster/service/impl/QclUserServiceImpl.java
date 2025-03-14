package com.ruoyi.qcluster.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qcluster.mapper.QclUserMapper;
import com.ruoyi.qcluster.domain.QclUser;
import com.ruoyi.qcluster.service.IQclUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-11
 */
@Service
public class QclUserServiceImpl implements IQclUserService 
{
    @Autowired
    private QclUserMapper qclUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public QclUser selectQclUserById(Long id)
    {
        return qclUserMapper.selectQclUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param qclUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<QclUser> selectQclUserList(QclUser qclUser)
    {
        return qclUserMapper.selectQclUserList(qclUser);
    }

    /**
     * 新增用户管理
     * 
     * @param qclUser 用户管理
     * @return 结果
     */
    @Override
    public int insertQclUser(QclUser qclUser)
    {
        return qclUserMapper.insertQclUser(qclUser);
    }

    /**
     * 修改用户管理
     * 
     * @param qclUser 用户管理
     * @return 结果
     */
    @Override
    public int updateQclUser(QclUser qclUser)
    {
        return qclUserMapper.updateQclUser(qclUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteQclUserByIds(Long[] ids)
    {
        return qclUserMapper.deleteQclUserByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteQclUserById(Long id)
    {
        return qclUserMapper.deleteQclUserById(id);
    }
}
