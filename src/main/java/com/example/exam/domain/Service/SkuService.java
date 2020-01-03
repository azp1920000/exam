package com.example.exam.domain.Service;


import com.example.exam.domain.controller.param.SkuParam;
import com.example.exam.domain.dto.SkuDTO;
import com.example.exam.domain.model.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuService {

    /**
     * 创建一条sku数据
     * @param sku
     * @return
     */
    Long createSkuInfo (Sku sku);

    /**
     * 批量创建sku
     * @param skuList
     * @return
     */
    Long createSkuInfos(List<Sku> skuList);

    /**
     * 根据ID查询sku信息
     * @param id
     * @return
     */
    SkuDTO getSkuById(Long id);

    /**
     * 根据多条件查询sku信息
     * @param skuDTO
     * @return
     */
    List<SkuDTO> getSkuInfo(SkuDTO skuDTO);

    /**
     * 根据id假删除sku
     * @param id
     * @return
     */
    Long deleteSkuById(Long id);

    /**
     * 根据所给多种信息进行更新
     * @param sku
     * @return
     */
    Long updateSkuById(Sku sku);
}
