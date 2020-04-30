package cn.itsource.service.impl;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.repository.IProducttypeRepository;
import cn.itsource.service.IProducttypeService;
import cn.itsource.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Producttype)表Service层接口
 *
 * @author 申林
 * @since 2020-04-30 11:50:13
 */
@Service
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype, Long> implements IProducttypeService {

    private IProducttypeRepository producttypeRepository;

    @Autowired
    public void setProducttypeRepository(IProducttypeRepository producttypeRepository) {
        this.producttypeRepository = producttypeRepository;
    }

    @Override
    public PageUtil<Producttype> findAllHasChild(ProducttypeQuery producttypeQuery) {
        Page pageByQuery = producttypeRepository.findPageByQuery(producttypeQuery);
        List<Producttype> producttypeList = producttypeRepository.findAll();
        PageUtil<Producttype> producttypePageUtil = new PageUtil<>();
        List<Producttype> stairProducttypeList = pageByQuery.getContent();/*分页对的一级菜单数据需要处理一下*/
        for (Producttype stairProducttype : stairProducttypeList) {
            stairProducttype.setChildren(getProducttypeTreeProducttype(producttypeList, stairProducttype.getId()));/*一级菜单*/
        }
        producttypePageUtil.setList(stairProducttypeList);
        producttypePageUtil.setCurrentPage(pageByQuery.getNumber() + 1);/*当前页数+1，是从0开始的*/
        producttypePageUtil.setTotalRows((int) pageByQuery.getTotalElements());
        return producttypePageUtil;
    }


    /**
     * 菜单管理，不用初始化菜单，不需要特殊处理url
     *
     * @param producttypeList
     * @param id
     * @return
     */
    private List<Producttype> getProducttypeTreeProducttype(List<Producttype> producttypeList, Long id) {
        List<Producttype> treeList = new ArrayList<>();
        for (Producttype producttype : producttypeList) {/*将所有菜单的parentid和传递的菜单id对比，相等就递归调用，并且加到treeList中，用于setChildren*/
            if (producttype.getParent() != null) {
                if (id.equals(producttype.getParent().getId())) {/*可能出现空指的放在后面*/
                    producttype.setChildren(getProducttypeTreeProducttype(producttypeList, producttype.getId()));
                    treeList.add(producttype);
                }
            }
        }
        return treeList;
    }
}