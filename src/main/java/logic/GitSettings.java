package logic;

public class GitSettings implements IGitSettings {

	private String gitDirectory;
	private String login;
	private String password;

	public GitSettings(String gitDirectory, String login, String password) {
		this.gitDirectory = gitDirectory;
		this.login = login;
		this.password = password;
	}

	public String getGitDirectory() {
		return gitDirectory;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GitSettings))
			return false;
		GitSettings gitSettings = (GitSettings)obj;
		if (gitSettings.getGitDirectory().equals(gitDirectory) &&
				gitSettings.getLogin().equals(login) &&
				gitSettings.getPassword().equals(password))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return gitDirectory+" "+login+" : "+password;
	}
}
