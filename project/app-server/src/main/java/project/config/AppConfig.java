package project.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.Bean;
import util.ComponentScan;
import util.SqlSessionFactoryProxy;

// Application을 실행하는데 필요한 객체를 설정하는 일을 한다.

@ComponentScan(basePackages={"project.dao", "project.handler"})
public class AppConfig {
  // Mybatis 객체 준비
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("project/config/mybatis-config.xml")));
  }
}
