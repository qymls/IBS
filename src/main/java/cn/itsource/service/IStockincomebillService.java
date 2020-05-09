package cn.itsource.service;

import cn.itsource.domain.Stockincomebill;

/**
 * (Stockincomebill)表Service层接口
 *
 * @author 申林
 * @since 2020-05-09 14:21:59
 */
public interface IStockincomebillService extends IBaseService<Stockincomebill, Long> {
    void audit(Stockincomebill stockincomebill);

}