package com.sxmd.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Description: 分页开始
 *
 * @author cy
 * @date 2020年08月26日 17:37
 * Version 1.0
 */
public class PageStart<T> extends Page<T> {

    private static final long serialVersionUID = 2841124360672467693L;

    public PageStart(long current, long size) {
        super(current, size);
    }
}
