package com.senseforce.framework.database;

import java.util.ArrayList;
import java.util.HashMap;

public class SFDatabase {

	private String databasePath = null;
	private HashMap<?, ?> cachedStatements = null;
	private boolean shouldCacheStatements = false;
	
	public static SFDatabase databaseWithPath(String path) {
		return new SFDatabase().initWithPath(path);
	}
	
	public SFDatabase() {
		//TODO
	}
	
	public SFDatabase initWithPath(String path) {
		return this;
	}
	
	public void open() {
		//TODO
	}
	
	public void close() {
		//TODO
	}
	
	public String databasePath() {
		return databasePath;
	}
	
	public boolean shouldCacheStatements() {
		return shouldCacheStatements;
	}
	
	public void setShouldCacheStatements(boolean shouldCacheStatements) {
		this.shouldCacheStatements = shouldCacheStatements;
	}
	
	public void executeUpdate(String sql, String... args) {
		//TODO
	}
	
	public void executeUpdate(String sql, ArrayList<Object> argumentsInArray) {
		//TODO
	}
	
	public void executeUpdate(String sql, ArrayList<Object> argumentsInArray, String... args) {
		//TODO
	}
	
	public void executeQuery(String sql, String... args) {
		//TODO
	}
	
	public void executeQuery(String sql, ArrayList<Object> argumentsInArray) {
		//TODO
	}
	
	public void executeQuery(String sql, ArrayList<Object> argumentsInArray, String... args) {
		//TODO
	}
	
	public void rollback() {
		//TODO
	}
	
	
	
	protected void destroy() {
		if (cachedStatements != null) {
			cachedStatements.clear();
			cachedStatements = null;
		}
	}
}
