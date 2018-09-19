package com.funi.distributedcomputer.mongodb.util;

import java.util.List;
/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/18 11:35
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public enum QueryRule {

    BETWEEN, EQ, LIKE, NOT_EQ, GT, GE, LT, LE,
    IN, NOT_IN, IS_NULL, IS_NOT_NULL, IS_EMPTY,
    IS_NOT_EMPTY, ASC_ORDER, DESC_ORDER;

    public static QueryRule getInstance() {
        return QueryRule.BETWEEN;
    }

    private List<Rule> ruleList;

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public static class Rule {

        private QueryRule type;
        private String[] values;

        private String andOr;

        private String propertyName;

        public QueryRule getType() {
            return type;
        }

        public String[] getValues() {
            return values;
        }

        public String getAndOr() {
            return andOr;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }
}
