package mybatis.mapper;

import mybatis.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Long empno);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Long empno);

    //    @Update({"<script>",
//            "update emp" +
//                    "        <set>" +
//                    "            <if test='ename != null'>" +
//                    "                ename = #{ename,jdbcType=VARCHAR}," +
//                    "            </if>" +
//                    "            <if test='job != null'>" +
//                    "                job = #{job,jdbcType=VARCHAR}," +
//                    "            </if>" +
//                    "            <if test='mgr != null'>" +
//                    "                mgr = #{mgr,jdbcType=DECIMAL}," +
//                    "            </if>" +
//                    "            <if test='hiredate != null'>" +
//                    "                hiredate = #{hiredate,jdbcType=DATE}," +
//                    "            </if>" +
//                    "            <if test='sal != null'>" +
//                    "                sal = #{sal,jdbcType=DECIMAL}," +
//                    "            </if>" +
//                    "            <if test='comm != null'>" +
//                    "                comm = #{comm,jdbcType=DECIMAL}," +
//                    "            </if>" +
//                    "            <if test='deptno != null'>" +
//                    "                deptno = #{deptno,jdbcType=DECIMAL}," +
//                    "            </if>" +
//                    "        </set>" +
//                    "        where empno = #{empno,jdbcType=DECIMAL}",
//            "</script>"})
    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> findAll();

    List<Emp> selectEmpsLike();
}