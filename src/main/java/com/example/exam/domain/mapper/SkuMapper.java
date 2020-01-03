package com.example.exam.domain.mapper;

import com.example.exam.domain.controller.param.SkuParam;
import com.example.exam.domain.dto.SkuDTO;

import com.example.exam.domain.model.Sku;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SkuMapper {

    /**
     * 创建
     * @param sku
     * @return
     */
    @Insert({
            "<script>",
            "INSERT INTO Sku (skuName,price,categoryId,brandId,description)",
            "VALUES (#{sku.skuName},#{sku.price},#{sku.categoryId},#{sku.brandId},#{sku.description})",
            "</script>",
    })
    @Options(useGeneratedKeys = true ,keyProperty = "skuId")
    Long createSkuInfo (@Param("sku") Sku sku);

    /**
     * 多条批量创建
     * @param skuList
     * @return
     */
    @Insert({
            "<script>",
            "INSERT INTO Sku (skuName,price,categoryId,brandId,description)",
            "VALUES",
            "<foreach collection= \"skuList\" item= \"sku\" index= \"index\" open=\"\" separator=\",\"  close= \"\" >",
            "(#{sku.skuName},#{sku.price},#{sku.categoryId},#{sku.brandId},#{sku.description})",
            "</foreach>",
            "</script>",
    })
    @Options(useGeneratedKeys = true ,keyProperty = "skuId")
    Long createSkuInfos(@Param("skuList")List<Sku> skuList);

    /**
     * 根据Id 查询商品信息
     * @param id
     * @return
     */
    @Select({
            "<script>",
            "SELECT s.skuId,s.skuName,s.price,c.categoryName,b.brandName,s.description,s.updateTime,s.categoryId,s.brandId",
            "FROM Sku as s",
            "JOIN Category as c ON s.categoryId = c.categoryId ",
            "JOIN Brand as b ON s.brandId = b.brandId ",
            "WHERE s.deleted = 0 and s.skuId = #{id}",
            "</script>",
    })
    SkuDTO getSkuById(@Param("id") Long id);

    /**
     * 根据所给多种条件进行查询
     * @param skuDTO
     * @return
     */
    @Select({
            "<script>",
            "SELECT s.skuId,s.skuName,s.price,c.categoryName,b.brandName,s.description,s.updateTime",
            "FROM Sku as s",
            "JOIN Category as c ON s.categoryId = c.categoryId ",
            "JOIN Brand as b ON s.brandId = b.brandId ",
            "WHERE s.deleted = 0 ",
            "<if test= \" skuDTO.skuId != null \">",
            "AND s.skuId = #{skuDTO.skuId}",
            "</if>",

            "<if test= \" skuDTO.skuName != null and skuDTO.skuName != '' \" >",
            "AND s.skuName LIKE CONCAT('%',#{skuDTO.skuName},'%')",
            "</if>",

            "<if test= \" skuDTO.categoryName != null and skuDTO.categoryName != '' \" >",
            "AND c.categoryName LIKE CONCAT('%',#{skuDTO.categoryName},'%')",
            "</if>",

            "<if test= \" skuDTO.brandName != null and skuDTO.brandName != '' \" >",
            "AND b.brandName LIKE CONCAT('%',#{skuDTO.brandName},'%')",
            "</if>",

            "<if test= \" skuDTO.startTime != null and skuDTO.endTime != null\" >",
            "AND s.updateTIme &gt;= #{skuDTO.startTime}",
            "AND s.updateTime &lt;= #{skuDTO.endTime}",
            "</if>",

            "<if test= \" skuDTO.categoryIds != null and skuDTO.categoryIds.size > 0 \">",
            "AND s.categoryId IN",
            "<foreach collection= \"skuDTO.categoryIds\" item= \"id\" index= \"index\" open= \"(\" separator= \",\" close=\")\" >",
            "#{id}",
            "</foreach>",
            "</if>",
            "LIMIT #{skuDTO.pageNum},#{skuDTO.pageSize}",
            "</script>",
    })
    List<SkuDTO> getSkuInfo(@Param("skuDTO") SkuDTO skuDTO);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update({
            "<script>",
            "UPDATE Sku SET deleted = 1 WHERE skuId = #{id}",
            "</script>",
    })
    Long deleteSkuById(@Param("id") Long id);

    /**
     * 根据自己需要更新sku表内容
     * @param sku
     * @return
     */
    @Update({
            "<script>",
            "UPDATE Sku",
            "<trim prefix= \"SET\" suffixOverrides= \",\" >",
            "<if test= \" sku.skuName != null and sku.skuName != '' \" >",
            "skuName = #{sku.skuName},",
            "</if>",

            "<if test= \" sku.price != null \" >",
            "price = #{sku.price},",
            "</if>",

            "<if test= \" sku.description != null and sku.description != '' \" >",
            "description = #{sku.description},",
            "</if>",

            "<if test= \" sku.brandId != null  \" >",
            "brandId = #{sku.brandId},",
            "</if>",

            "<if test= \" sku.categoryId != null  \" >",
            "categoryId = #{sku.categoryId},",
            "</if>",

            "updateTime = now(),",
            "</trim>",
            "WHERE skuId = #{sku.skuId} AND deleted = 0 ",
            "</script>",
    })
    Long updateSkuById(@Param("sku") Sku sku);


}
