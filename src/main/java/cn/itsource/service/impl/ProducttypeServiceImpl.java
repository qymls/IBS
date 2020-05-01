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
import java.util.Comparator;
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
        List<Producttype> stairProducttypeList = pageByQuery.getContent();/*分页对的一级数据需要处理一下*/
        for (Producttype stairProducttype : stairProducttypeList) {
            stairProducttype.setChildren(getProducttypeTreeProducttype(producttypeList, stairProducttype.getId()));
        }
        producttypePageUtil.setList(stairProducttypeList);
        producttypePageUtil.setCurrentPage(pageByQuery.getNumber() + 1);/*当前页数+1，是从0开始的*/
        producttypePageUtil.setTotalRows((int) pageByQuery.getTotalElements());
        return producttypePageUtil;
    }

    /**
     * @param producttypeList
     * @param id
     * @return
     */
    private List<Producttype> getProducttypeTreeProducttype(List<Producttype> producttypeList, Long id) {
        List<Producttype> treeList = new ArrayList<>();
        for (Producttype producttype : producttypeList) {
            if (producttype.getParent() != null) {
                if (id.equals(producttype.getParent().getId())) {
                    producttype.setChildren(getProducttypeTreeProducttype(producttypeList, producttype.getId()));
                    treeList.add(producttype);
                }
            }
        }
        return treeList;
    }

    @Override
    public List<Long> findAllParentByID(Long id) {
        List<Long> producttypeListParent = new ArrayList<>();
        Producttype updateProducttype = producttypeRepository.findOne(id);/*修改的菜单*/
        getPartnt(updateProducttype, producttypeListParent);
        producttypeListParent.sort(Long::compareTo);
        return producttypeListParent;
    }

    /**
     * @param updateProducttype
     * @param list
     */
    public void getPartnt(Producttype updateProducttype, List<Long> list) {
        Producttype parent = updateProducttype.getParent();
        if (parent != null) {
            list.add(parent.getId());
            getPartnt(parent, list);
        }

    }
}