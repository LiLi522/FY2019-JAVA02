package com.lili.dao;

import com.lili.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    List<Order> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Order record);

    /**
     * 根据订单编号查询订单信息
     * @param orderNo
     * @return
     */
    Order findOrderByOrderNo(@Param("orderNo") Long orderNo);

    /**
     * 根据订单编号修改时间和订单状态
     * @param order
     * @return
     */
    int updateStatusAndTimeByOrderNo(@Param("order") Order order);

    /**
     * 根据订单创建时间查询订单
     * @param time
     * @return
     */
    List<Order> selectOrdersByCreateTime(@Param("time") String time);

    Integer closeOrder(@Param("id") Integer id);
}