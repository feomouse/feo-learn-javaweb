package doa.mybatisWappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import domin.Person;

public interface IPersonWapper {
	@Insert("insert into Person (id, name, sex) values (#{id}, #{name}, #{sex})")
    public int insertPerson(Person person);

	@Delete("delete from Person where name = #{name}")
    public int deletePerson(@Param("name") String name);
	
	@Select("select sex from Person where name = #{name}") 
    public String getPersonSexByName(@Param("name") String name);
    
	@Update("update Person set name = #{newName} where name = #{oldName}")
    public int setPersonName(@Param("oldName") String oldName, @Param("newName") String newName);
    
	@Select("select name from Person where id = #{id}")
    public String getNameById(@Param("id") Integer id);
}
