package cn.itsource.service.impl;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.repository.IProducttypeRepository;
import cn.itsource.service.IProducttypeService;
import cn.itsource.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * 重写findAll方法
     *
     * @return
     */
    @Override
    public List<Producttype> findAll() {/*重写FindAll方法，应为需要特殊处理下才能返回的数据*/
        List<Producttype> producttypeList = producttypeRepository.findAll();
        List<Producttype> stairProducttypeList = producttypeRepository.getStairProducttype();
        for (Producttype stairProducttype : stairProducttypeList) {
            stairProducttype.setChildren(getProducttypeTree(producttypeList, stairProducttype.getId()));/*一级菜单*/
        }
        return stairProducttypeList;
    }

    /**
     * @param producttypeList
     * @param id
     * @return
     */
    private List<Producttype> getProducttypeTree(List<Producttype> producttypeList, Long id) {
        List<Producttype> treeList = new ArrayList<>();
        for (Producttype producttype : producttypeList) {/*将所有菜单的parentid和传递的菜单id对比，相等就递归调用，并且加到treeList中，用于setChildren*/
            if (producttype.getParent() != null) {/*排除一级菜单在进来对比的情况*/
                if (id.equals(producttype.getParent().getId())) {/*可能出现空指的放在后面*/
                    producttype.setChildren(getProducttypeTree(producttypeList, producttype.getId()));
                    treeList.add(producttype);
                }
            }
        }
        return treeList;
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
        Collections.reverse(producttypeListParent);/*翻转元素，让第一级菜单在第一个位置*/
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

    /**
     * 保存数据返回主键
     *
     * @param producttype
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Long saveReturnParam(Producttype producttype) {
        producttypeRepository.save(producttype);
        return producttype.getId();
    }

    /**
     * 获得所有最后一级的商品类型
     *
     * @return
     */
    @Override
    public List<Producttype> findAllLastProducttype() {
        List<Producttype> producttypeItemList = new ArrayList<>();
        List<Producttype> allProducttype = findAll();/*得到所有菜单的数据已经是有children的数据了*/
        getLastProducttypeItem(allProducttype, producttypeItemList);
        return producttypeItemList;
    }

    public void getLastProducttypeItem(List<Producttype> producttypeList, List<Producttype> producttypeItemList) {
        for (Producttype producttype : producttypeList) {
            if (producttype.getChildren().size() > 0) {
                getLastProducttypeItem(producttype.getChildren(), producttypeItemList);
            } else {
                producttypeItemList.add(producttype);
            }

        }
    }
}