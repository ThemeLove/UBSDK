package com.umbrella.game.ubsdk.bean;

public class UBRoleInfo {
	private String serverId="0";		//服务器id
	private String serverName="";		//服务器名字
	private String roleId="0";			//角色id
	private String roleName="";			//角色名字
	private String roleLevel="";		//角色等级
	private String roleCreateTime="";	//角色创建时间
	private String vipLevel="";			//vip等级
	private String gameBalance="";		//游戏币余额
	private String partyName="";		//公会名称
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getRoleCreateTime() {
		return roleCreateTime;
	}
	public void setRoleCreateTime(String roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}
	public String getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}
	public String getGameBalance() {
		return gameBalance;
	}
	public void setGameBalance(String gameBalance) {
		this.gameBalance = gameBalance;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	@Override
	public String toString() {
		return "UBRoleInfo [serverId=" + serverId + ", serverName=" + serverName + ", roleId=" + roleId + ", roleName="
				+ roleName + ", roleLevel=" + roleLevel + ", roleCreateTime=" + roleCreateTime + ", vipLevel="
				+ vipLevel + ", gameBalance=" + gameBalance + ", partyName=" + partyName + "]";
	}
}
