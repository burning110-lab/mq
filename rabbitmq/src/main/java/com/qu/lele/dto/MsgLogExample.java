package com.qu.lele.dto;

import java.util.ArrayList;
import java.util.List;

public class MsgLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public MsgLogExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNull() {
            addCriterion("exchange is null");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNotNull() {
            addCriterion("exchange is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeEqualTo(String value) {
            addCriterion("exchange =", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotEqualTo(String value) {
            addCriterion("exchange <>", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThan(String value) {
            addCriterion("exchange >", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThanOrEqualTo(String value) {
            addCriterion("exchange >=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThan(String value) {
            addCriterion("exchange <", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThanOrEqualTo(String value) {
            addCriterion("exchange <=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLike(String value) {
            addCriterion("exchange like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotLike(String value) {
            addCriterion("exchange not like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeIn(List<String> values) {
            addCriterion("exchange in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotIn(List<String> values) {
            addCriterion("exchange not in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeBetween(String value1, String value2) {
            addCriterion("exchange between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotBetween(String value1, String value2) {
            addCriterion("exchange not between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyIsNull() {
            addCriterion("routingKey is null");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyIsNotNull() {
            addCriterion("routingKey is not null");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyEqualTo(String value) {
            addCriterion("routingKey =", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyNotEqualTo(String value) {
            addCriterion("routingKey <>", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyGreaterThan(String value) {
            addCriterion("routingKey >", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyGreaterThanOrEqualTo(String value) {
            addCriterion("routingKey >=", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyLessThan(String value) {
            addCriterion("routingKey <", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyLessThanOrEqualTo(String value) {
            addCriterion("routingKey <=", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyLike(String value) {
            addCriterion("routingKey like", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyNotLike(String value) {
            addCriterion("routingKey not like", value, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyIn(List<String> values) {
            addCriterion("routingKey in", values, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyNotIn(List<String> values) {
            addCriterion("routingKey not in", values, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyBetween(String value1, String value2) {
            addCriterion("routingKey between", value1, value2, "routingkey");
            return (Criteria) this;
        }

        public Criteria andRoutingkeyNotBetween(String value1, String value2) {
            addCriterion("routingKey not between", value1, value2, "routingkey");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTrycountIsNull() {
            addCriterion("tryCount is null");
            return (Criteria) this;
        }

        public Criteria andTrycountIsNotNull() {
            addCriterion("tryCount is not null");
            return (Criteria) this;
        }

        public Criteria andTrycountEqualTo(Integer value) {
            addCriterion("tryCount =", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountNotEqualTo(Integer value) {
            addCriterion("tryCount <>", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountGreaterThan(Integer value) {
            addCriterion("tryCount >", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountGreaterThanOrEqualTo(Integer value) {
            addCriterion("tryCount >=", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountLessThan(Integer value) {
            addCriterion("tryCount <", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountLessThanOrEqualTo(Integer value) {
            addCriterion("tryCount <=", value, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountIn(List<Integer> values) {
            addCriterion("tryCount in", values, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountNotIn(List<Integer> values) {
            addCriterion("tryCount not in", values, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountBetween(Integer value1, Integer value2) {
            addCriterion("tryCount between", value1, value2, "trycount");
            return (Criteria) this;
        }

        public Criteria andTrycountNotBetween(Integer value1, Integer value2) {
            addCriterion("tryCount not between", value1, value2, "trycount");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeIsNull() {
            addCriterion("nextTryTime is null");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeIsNotNull() {
            addCriterion("nextTryTime is not null");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeEqualTo(Long value) {
            addCriterion("nextTryTime =", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeNotEqualTo(Long value) {
            addCriterion("nextTryTime <>", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeGreaterThan(Long value) {
            addCriterion("nextTryTime >", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeGreaterThanOrEqualTo(Long value) {
            addCriterion("nextTryTime >=", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeLessThan(Long value) {
            addCriterion("nextTryTime <", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeLessThanOrEqualTo(Long value) {
            addCriterion("nextTryTime <=", value, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeIn(List<Long> values) {
            addCriterion("nextTryTime in", values, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeNotIn(List<Long> values) {
            addCriterion("nextTryTime not in", values, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeBetween(Long value1, Long value2) {
            addCriterion("nextTryTime between", value1, value2, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andNexttrytimeNotBetween(Long value1, Long value2) {
            addCriterion("nextTryTime not between", value1, value2, "nexttrytime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createTime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createTime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(String value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(String value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(String value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(String value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(String value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLike(String value) {
            addCriterion("updateTime like", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotLike(String value) {
            addCriterion("updateTime not like", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<String> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<String> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(String value1, String value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(String value1, String value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andAlertidIsNull() {
            addCriterion("alertId is null");
            return (Criteria) this;
        }

        public Criteria andAlertidIsNotNull() {
            addCriterion("alertId is not null");
            return (Criteria) this;
        }

        public Criteria andAlertidEqualTo(String value) {
            addCriterion("alertId =", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidNotEqualTo(String value) {
            addCriterion("alertId <>", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidGreaterThan(String value) {
            addCriterion("alertId >", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidGreaterThanOrEqualTo(String value) {
            addCriterion("alertId >=", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidLessThan(String value) {
            addCriterion("alertId <", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidLessThanOrEqualTo(String value) {
            addCriterion("alertId <=", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidLike(String value) {
            addCriterion("alertId like", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidNotLike(String value) {
            addCriterion("alertId not like", value, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidIn(List<String> values) {
            addCriterion("alertId in", values, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidNotIn(List<String> values) {
            addCriterion("alertId not in", values, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidBetween(String value1, String value2) {
            addCriterion("alertId between", value1, value2, "alertid");
            return (Criteria) this;
        }

        public Criteria andAlertidNotBetween(String value1, String value2) {
            addCriterion("alertId not between", value1, value2, "alertid");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}