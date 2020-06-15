package com.liyue.liyueniubi.domain;

import com.liyue.liyueniubi.Enum.ConstantsUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Order implements Delayed, Serializable {
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer state;

    private Date createtime;

    private Date updatetime;

    private String orderno;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }
    @Override
    public long getDelay(TimeUnit unit) {
        // ORDER_TIME_OUT = 1800000L
        return (this.createtime.getTime() + ConstantsUtil.ORDER_TIME_OUT) - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o) {
            return 0;
        }
        Order order= (Order) o;
        long time = this.createtime.getTime() - order.getCreatetime().getTime();
        return time == 0 ? 0 : time < 0 ? -1 : 1;

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Order other = (Order) obj;
        if (other.getOrderno() == null) {
            return false;
        } else if (!id.equals(other.getId())) {
            return false;
        } else if (!orderno.equals(other.getOrderno())) {
            return false;
        }
        return true;
    }


}