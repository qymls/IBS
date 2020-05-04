package cn.itsource.service;

import cn.itsource.domain.Systemdictionarydetail;

import java.util.List;

/**
 * (Systemdictionarydetail)表Service层接口
 *
 * @author 申林
 * @since 2020-04-30 11:50:12
 */
public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail,Long>{
    List<Systemdictionarydetail> findBySn(String sn);

}