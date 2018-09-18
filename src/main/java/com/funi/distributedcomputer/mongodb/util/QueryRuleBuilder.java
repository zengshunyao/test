package com.funi.distributedcomputer.mongodb.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryRuleBuilder {

    private Query query;

    private Criteria criteria;

    private StringBuilder builder;

    private List<String> orders;

    public QueryRuleBuilder(QueryRule queryRule) {
        //根据RuleList 来循环，动态生成各种Criteria
//        queryRule.getRuleList();
        for (QueryRule.Rule rule : queryRule.getRuleList()) {
            switch (rule.getType()) {
                case BETWEEN:
                    System.out.println();
                    this.processBetween(rule);
                    break;
                case EQ:
                    break;
                case LIKE:
                    break;
                case NOT_EQ:
                    break;
                case GT:
                    break;
                case GE:
                    break;
                case LT:
                    break;
                case LE:
                    break;
                case NOT_IN:
                    break;
                case IS_NULL:
                    break;
                case IS_NOT_NULL:
                    break;
                case IS_EMPTY:
                    break;
                case IS_NOT_EMPTY:
                    break;
                case ASC_ORDER:
                    break;
                case DESC_ORDER:
                    break;
            }
        }
        this.query = new Query(this.criteria);

        //排序
        List<Sort.Order> orders = new LinkedList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "age"));
        this.query.with(new Sort(orders));
    }

    public Query getQuery() {
        return query;
    }

    /**
     * 去掉order
     *
     * @param sql
     * @return
     */
    protected String removeOrders(String sql) {
        Pattern pattern = Pattern.compile
                ("order\\s*by[\\w|\\W|\\s|\\S]]*",
                        Pattern.CASE_INSENSITIVE);

        Matcher match = pattern.matcher(sql);
        StringBuffer builder = new StringBuffer();
        while (match.find()) {
            match.appendReplacement(builder, "");
        }
        match.appendTail(builder);
        return builder.toString();
    }

    /**
     * 删除select部分
     *
     * @param sql
     * @return
     */
    protected String removeSelect(String sql) {
        if (sql.toLowerCase().matches("from\\s+")) {
            int beginPos = sql.toLowerCase().indexOf("from");
            return sql.substring(beginPos);
        } else {
            return sql;
        }
    }

    private void processBetween(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues()) || rule.getValues().length < 2) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "", "between", rule.getValues()[0], "and");
            add(0, "", "", "", rule.getValues()[0], "");
        }
    }

    private void processEqual(QueryRule.Rule rule) {
        this.criteria.and(rule.getPropertyName()).is(rule.getValues()[0]);
    }

    /**
     * @param rule
     */
    private void processLike(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "like", rule.getValues()[0]);
        }
    }

    private void processNotEqual(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "<>", rule.getValues()[0]);
        }
    }

    private void processGreaterThan(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), ">", rule.getValues()[0]);
        }
    }

    private void processGreaterEqual(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), ">=", rule.getValues()[0]);
        }
    }

    private void processLessThan(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "<", rule.getValues()[0]);
        }
    }

    private void processLessEqual(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "<=", rule.getValues()[0]);
        }
    }

    private void processIn(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < rule.getValues().length; i++) {
                String val = rule.getValues()[i];
                builder.append(val);
                if (i + 1 != rule.getValues().length) {
                    builder.append(",");
                }
            }
            add(rule.getAndOr(), rule.getPropertyName(), "in", "(", builder.toString(), ")");
        }
    }

    private void processNotIn(QueryRule.Rule rule) {
        if (ArrayUtils.isEmpty(rule.getValues())) {
            return;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < rule.getValues().length; i++) {
                String val = rule.getValues()[i];
                builder.append(val);
                if (i + 1 != rule.getValues().length) {
                    builder.append(",");
                }
            }
            add(rule.getAndOr(), rule.getPropertyName(), "not in", "(", builder.toString(), ")");
        }
    }

    private void processIsNull(QueryRule.Rule rule) {
        if (null == (rule.getPropertyName())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "is null");
        }
    }

    private void processIsNotNull(QueryRule.Rule rule) {
        if (null == (rule.getPropertyName())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "is not null");
        }
    }

    private void processIsEmpty(QueryRule.Rule rule) {
        if (null == (rule.getPropertyName())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "=", "''");
        }
    }

    private void processIsNotEmpty(QueryRule.Rule rule) {
        if (null == (rule.getPropertyName())) {
            return;
        } else {
            add(rule.getAndOr(), rule.getPropertyName(), "<>", "''");
        }
    }


    private void processOrder(QueryRule.Rule rule) {
        if (rule.getPropertyName() == null) {
            return;
        } else {
            switch (rule.getType()) {
                case ASC_ORDER:
                    orders.add(new Order(rule.getPropertyName(), true).getSqlPart());
                    break;
                case DESC_ORDER:
                    orders.add(new Order(rule.getPropertyName(), false).getSqlPart());
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * @param values
     */
    private void add(Object... values) {
        for (Object val : values) {
            this.builder.append(val).append(" ");
        }
    }
}
