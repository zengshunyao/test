package category.jdk.java18;

import java.io.Serializable;
import java.util.Objects;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 18:17
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class Employee implements Serializable, Cloneable {
    private static final long serialVersionUID = -2463109228077677447L;
    private String name = "未知";
    private String from = "北京";
    private int salary = 1800;
    private int age = 18;
    private Status status = Employee.Status.FREE;

    public Employee() {
        super();
    }

    public Employee(String name, int salary, int age, Status status) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.status = status;
    }

    public enum Status {
        FREE, BUSY, VOCATION;

        @Override
        public String toString() {
            return String.format("Status{%s}", this.name());
        }
    }

    public Employee(String name, String from, int salary, int age) {
        this.name = name;
        this.from = from;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getSalary() == employee.getSalary() &&
                getAge() == employee.getAge() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getFrom(), employee.getFrom()) &&
                getStatus() == employee.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFrom(), getSalary(), getAge(), getStatus());
    }
}
