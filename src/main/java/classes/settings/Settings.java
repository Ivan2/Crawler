package classes.settings;

import interfaces_abstracts.settings.ISettings;

public class Settings implements ISettings {

	private String intermediateDBName;
	private String intermediateDBUserName;
	private String intermediateDBPassword;
	private String intermediateDBHost;
	private String intermediateDBPort;
	private String finalDBName;
	private String finalDBUserName;
	private String finalDBPassword;
	private String finalDBHost;
	private String finalDBPort;
	private String rabbitMQHost;

	public void setParams(String intermediateDBName, String intermediateDBUserName,
	                String intermediateDBPassword, String intermediateDBHost,
	                String intermediateDBPort, String finalDBName,
	                String finalDBUserName, String finalDBPassword,
	                String finalDBHost, String finalDBPort,
	                String rabbitMQHost) {
		this.intermediateDBName = intermediateDBName;
		this.intermediateDBUserName = intermediateDBUserName;
		this.intermediateDBPassword = intermediateDBPassword;
		this.intermediateDBHost = intermediateDBHost;
		this.intermediateDBPort = intermediateDBPort;
		this.finalDBName = finalDBName;
		this.finalDBUserName = finalDBUserName;
		this.finalDBPassword = finalDBPassword;
		this.finalDBHost = finalDBHost;
		this.finalDBPort = finalDBPort;
		this.rabbitMQHost = rabbitMQHost;
	}

	public void setFinalDBPort(String finalDBPort) {
		this.finalDBPort = finalDBPort;
	}

	public void setIntermediateDBPort(String intermediateDBPort) {
		this.intermediateDBPort = intermediateDBPort;
	}

	public String getIntermediateDBName() {
		return intermediateDBName;
	}

	public String getIntermediateDBUserName() {
		return intermediateDBUserName;
	}

	public String getIntermediateDBPassword() {
		return intermediateDBPassword;
	}

	public String getIntermediateDBHost() {
		return intermediateDBHost;
	}

	public String getIntermediateDBPort() {
		return intermediateDBPort;
	}

	public String getFinalDBName() {
		return finalDBName;
	}

	public String getFinalDBUserName() {
		return finalDBUserName;
	}

	public String getFinalDBPassword() {
		return finalDBPassword;
	}

	public String getFinalDBHost() {
		return finalDBHost;
	}

	public String getFinalDBPort() {
		return finalDBPort;
	}

	public String getRabbitMQHost() {
		return rabbitMQHost;
	}
}
