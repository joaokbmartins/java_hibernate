package com.joao.jpa_introduction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA - Java Persistence API: specification implemented by ORM tools such as
 * Hibernate;
 * *
 * Steps:
 *  Add Dependencies:
 *    <dependency>
 *    	<groupId>org.hibernate</groupId>
 *    	<artifactId>hibernate-core</artifactId>
 *    	<version>5.4.10.Final</version>
 *    </dependency>
 *    <dependency>
 *    	<groupId>mysql</groupId>
 *    	<artifactId>mysql-connector-java</artifactId>
 *    	<version>8.0.26</version>
 *    </dependency>
 *  Create file: src/main/resources/META-INF/persistence.xml
 *    <?xml version="1.0" encoding="UTF-8"?>
 *    <persistence version="2.1"
 *    	xmlns="http://xmlns.jcp.org/xml/ns/persistence"
 *    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *    	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
 *    	<persistence-unit name="pu-mysql">
 *    		<properties>
 *    			<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
 *    			<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/DATABASE"/>
 *    			<property name="javax.persistence.jdbc.user" value="USERNAME"/>
 *    			<property name="javax.persistence.jdbc.password" value="PASSWORD"/>
 *    			<property name="javax.persistence.schema-generation.database.action" value="none"/>
 *    		</properties>
 *    	</persistence-unit>
 *    </persistence>
 *  EntityManagerFactry > EntityManager
 */
public class AppJPA {

	public static void main(String[] args) {

		Dog dog1 = new Dog();
		dog1.setId(1);
		dog1.setColor("Black");
		dog1.setType("BullTerrier");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-mysql");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

//		em.persist(dog1);

		Dog dog2 = em.find(Dog.class, 1);

		em.getTransaction().commit();
		em.close();

		System.out.println("Dog2: " + dog2);

	}

}
