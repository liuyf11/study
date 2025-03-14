package com.ruoyi.qcluster.mapper;

import java.util.List;
import com.ruoyi.qcluster.domain.QclUser;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-11
 */
public interface QclUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public QclUser selectQclUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param qclUser 用户管理
     * @return 用户管理集合
     */
    public List<QclUser> selectQclUserList(QclUser qclUser);

    /**
     * 新增用户管理
     * 
     * @param qclUser 用户管理
     * @return 结果
     */
    public int insertQclUser(QclUser qclUser);

    /**
     * 修改用户管理
     * 
     * @param qclUser 用户管理
     * @return 结果
     */
    public int updateQclUser(QclUser qclUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteQclUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQclUserByIds(Long[] ids);
}
