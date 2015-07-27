package settings;

public class Settings {

	private String dbUserName;
	private String dbPassword;
	private String appName;

	public Settings(String dbUserName, String dbPassword, String appName) {
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		this.appName = appName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getAppName() {
		return appName;
	}
}
