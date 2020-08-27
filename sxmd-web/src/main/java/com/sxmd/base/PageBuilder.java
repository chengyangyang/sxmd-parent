package com.sxmd.base;

import com.sxmd.util.AssertUtil;
import com.sxmd.util.BeanUtil;
import com.sxmd.util.CollectionUtil;

import java.util.List;

/**
 * Description:  分页构建类  web 端使用这个,使用mybatis-plus 分页插件
 *
 * @author cy
 * @date 2020年08月26日 17:43
 * Version 1.0
 */
public class PageBuilder {

    private PageBuilder() {
    }

    /**
     * Description:   构建 mybatis-plus 一个开始行
     *
     * @param page:
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.PageStart<T>
     * @author cy
     * @date 2020/8/26 17:26
     */
    public static <T> PageStart<T> builderPageStart(RequestPage page) {
        if (page != null) {
            builderPageStart(page.getPageNum(), page.getPageSize());
        }
        return null;
    }


    /**
     * Description:   构建 mybatis-plus 一个开始行
     *
     * @param pageNum:  页码
     * @param pageSize: 大小
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.PageStart<T>
     * @author cy
     * @date 2020/8/26 17:26
     */
    public static <T> PageStart<T> builderPageStart(Long pageNum, Long pageSize) {
        if (pageNum != null && pageSize != null) {
            return new PageStart<>(pageNum, pageSize);
        } else {
            new PageStart<>(1L, 10L);
        }
        return null;
    }

    /**
     * Description:   构建返回分页
     *
     * @param page:
     * @param list:
     * @return com.sxmd.base.BasePage<T>
     * @author cy
     * @date 2020/8/26 17:35
     */
    public static <T> BasePage<T> builderResultPage(PageStart<?> page, List<T> list) {
        BasePage<T> basePage = new BasePage<>();
        basePage.setContent(list);
        if (page != null) {
            Pageable pageable = new Pageable();
            pageable.setTotal(page.getTotal());
            pageable.setPageNum(page.getCurrent());
            pageable.setPages(page.getPages());
            pageable.setPageSize(page.getSize());
        }
        return basePage;
    }

    /**
     * Description:   basePage 转换
     *
     * @param sourceBasePage:
     * @param targetClass:
     * @return com.sxmd.base.BasePage<T>
     * @author cy
     * @date 2020/8/26 18:04
     */
    public static <T> BasePage<T> basePageConversion(BasePage<?> sourceBasePage, Class<T> targetClass) {
        // 实体类转换
        BasePage<T> targetBasePage = new BasePage<>();
        AssertUtil.notNull(sourceBasePage, "sourceBasePage 不能为空");
        AssertUtil.notNull(targetClass, "clazz 不能为空");
        targetBasePage.setPageable(sourceBasePage.getPageable());
        if (CollectionUtil.isNotBlank(sourceBasePage.getContent())) {
            targetBasePage.setContent(BeanUtil.copyPropertiesOfList(sourceBasePage.getContent(), targetClass));
        }
        return targetBasePage;
    }

}
