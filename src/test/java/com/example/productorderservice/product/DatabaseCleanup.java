package com.example.productorderservice.product;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseCleanup implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;


    /**
     * EntityManager에서 Entity 모두 가져오고  Entity/Table 어노테이션 있는지 확인
     * tableNames에 해당 table의 이름 담는다. 그후에 entity가 붙어있으면서 table 어노테이션이 없는애들은
     * 카멜케이스를 snake형식으로 변경, 구글 구아바 라이브러리 추가 필요
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> e.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toList());

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .toList();

    }

    private boolean isEntity(EntityType<?> e) {
        return null != e.getJavaType().getAnnotation((Entity.class));
    }

    private boolean hasTableAnnotation(EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL INTEGRITY FALSE").executeUpdate(); //참조무결성 무시
        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            //GeneratedValue로 자동으로 올라가는 sequence를 1로 초기화
        }

        entityManager.createNativeQuery("SET REFRENTIAL_INTEGRITY TRUE").executeUpdate();
    }

}
