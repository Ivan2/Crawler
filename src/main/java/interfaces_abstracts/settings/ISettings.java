package interfaces_abstracts.settings;

public interface ISettings {

	void setParams(String intermediateDBName, String intermediateDBUserName,
	               String intermediateDBPassword, String intermediateDBHost,
	               String intermediateDBPort, String finalDBName,
	               String finalDBUserName, String finalDBPassword,
	               String finalDBHost, String finalDBPort,
	               String rabbitMQHost);

	void setFinalDBPort(String finalDBPort);

	void setIntermediateDBPort(String intermediateDBPort);

	String getIntermediateDBName();

	String getIntermediateDBUserName();

	String getIntermediateDBPassword();

	String getIntermediateDBHost();

	String getIntermediateDBPort();

	String getFinalDBName();

	String getFinalDBUserName();

	String getFinalDBPassword();

	String getFinalDBHost();

	String getFinalDBPort();

	String getRabbitMQHost();

}
