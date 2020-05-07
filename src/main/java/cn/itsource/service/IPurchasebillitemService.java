package cn.itsource.service;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.domain.vo.PurchaseBillItemVo;
import cn.itsource.domain.vo.SeriesLine;
import cn.itsource.query.PurchasebillitemQuery;

import java.util.List;
import java.util.Map;

/**
 * (Purchasebillitem)表Service层接口
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
public interface IPurchasebillitemService extends IBaseService<Purchasebillitem, Long> {
    List<PurchaseBillItemVo> findAllVo(PurchasebillitemQuery purchasebillitemQuery);

    List<Map<String,Object>> findChartsPie(PurchasebillitemQuery purchasebillitemQuery);

    List<SeriesLine> findChartsLine(PurchasebillitemQuery purchasebillitemQuery);

}