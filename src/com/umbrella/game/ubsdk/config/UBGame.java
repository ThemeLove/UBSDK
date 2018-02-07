package com.umbrella.game.ubsdk.config;

public class UBGame {
	public static boolean debugMode=true;
	private String ubGameID;
	private String orientation;
	private String mainActivityName;
	
	public boolean isDebugMode() {
		return debugMode;
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	public String getUbGameID() {
		return ubGameID;
	}
	public void setUbGameID(String ubGameID) {
		this.ubGameID = ubGameID;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getMainActivityName() {
		return mainActivityName;
	}
	public void setMainActivityName(String mainActivityName) {
		this.mainActivityName = mainActivityName;
	}

	
}
