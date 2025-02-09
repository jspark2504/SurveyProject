package com.sample.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SurveyDbConfig {
	@Bean(name="surveyDataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource surveyDataSource() {
		log.debug("surveyDataSource bean load");
		return DataSourceBuilder.create().build();
	}
    
    @Bean(name="surveySqlSessionFactory")
    public SqlSessionFactory surveySqlSessionFactory(@Qualifier("surveyDataSource")  DataSource dbDataSource, ApplicationContext applicationContext) throws Exception  {
		SqlSessionFactoryBean sqlSessionFactoryBean = new  SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dbDataSource);
		
        //커밋 정책은 하나의 디비인데...그 외의 디비는 수동 커밋시키기 위해
        JdbcTransactionFactory trn= new JdbcTransactionFactory();
        trn.newTransaction(dbDataSource, null, false);  //트랜젝션 설정에서 기본 auto commit 값을 false로 해 줍니다.
        sqlSessionFactoryBean.setTransactionFactory(trn);
        // MyBatis 설정 적용
        org.apache.ibatis.session.Configuration myBatisConfig = new org.apache.ibatis.session.Configuration();
        myBatisConfig.setMapUnderscoreToCamelCase(true); // 설정 적용
        myBatisConfig.setDefaultStatementTimeout(5000); // 5000초로 설정
        myBatisConfig.setCallSettersOnNulls(true);// null 값이 반환될 때 setter 호출
        sqlSessionFactoryBean.setConfiguration(myBatisConfig);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.sample.survey.dto"); // SurveyDto의 패키지 경로 설정 -> 와일드 카드 적용 안됨 , 로 구분
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/survey/*.xml")); 
		return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "surveySqlSessionTemplate") 
    public SqlSessionTemplate surveySqlSessionTemplate(@Qualifier("surveySqlSessionFactory") SqlSessionFactory  dbSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(dbSqlSessionFactory, ExecutorType.SIMPLE);
    }
}
