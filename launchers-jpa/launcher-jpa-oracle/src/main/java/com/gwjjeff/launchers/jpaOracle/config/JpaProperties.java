package com.gwjjeff.launchers.jpaOracle.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jpaOracle")
@ConfigurationProperties(prefix = "spring.jpaOracle.jpa")
public class JpaProperties {
	/**
	 * Name of the target database to operate on, auto-detected by default. Can be
	 * alternatively set using the "Database" enum.
	 */
	private String databasePlatform;

	/**
	 * Initialize the schema on startup.
	 */
	private boolean generateDdl = false;

	/**
	 * Enable logging of SQL statements.
	 */
	private boolean showSql = false;

	private Hibernate hibernate = new Hibernate();

	public String getDatabasePlatform() {
		return databasePlatform;
	}

	public void setDatabasePlatform(String databasePlatform) {
		this.databasePlatform = databasePlatform;
	}

	public boolean isGenerateDdl() {
		return this.generateDdl;
	}

	public void setGenerateDdl(boolean generateDdl) {
		this.generateDdl = generateDdl;
	}

	public boolean isShowSql() {
		return this.showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	public Hibernate getHibernate() {
		return this.hibernate;
	}

	public void setHibernate(Hibernate hibernate) {
		this.hibernate = hibernate;
	}

	public static class Hibernate {

		/**
		 * DDL mode. This is actually a shortcut for the "hibernate.hbm2ddl.auto"
		 * property. Default to "create-drop" when using an embedded database, "none"
		 * otherwise.
		 */
		private String ddlAuto;

		/**
		 * Use Hibernate's newer IdentifierGenerator for AUTO, TABLE and SEQUENCE. This is
		 * actually a shortcut for the "hibernate.id.new_generator_mappings" property.
		 * When not specified will default to "false" with Hibernate 5 for back
		 * compatibility.
		 */
		private Boolean useNewIdGeneratorMappings;

		public String getDdlAuto() {
			return this.ddlAuto;
		}

		public void setDdlAuto(String ddlAuto) {
			this.ddlAuto = ddlAuto;
		}

		public boolean isUseNewIdGeneratorMappings() {
			return this.useNewIdGeneratorMappings;
		}

		public void setUseNewIdGeneratorMappings(boolean useNewIdGeneratorMappings) {
			this.useNewIdGeneratorMappings = useNewIdGeneratorMappings;
		}

	}

}
