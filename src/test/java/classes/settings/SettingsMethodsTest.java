package classes.settings;

import classes.service_locator.ServiceLocatorAdapter;
import abstractions.settings.ISettings;
import org.junit.Assert;

public class SettingsMethodsTest {

	private String intermediateDBName = "1";
	private String intermediateDBUserName = "2";
	private String intermediateDBPassword = "3";
	private String intermediateDBHost = "4";
	private String intermediateDBPort = "5";
	private String finalDBName = "6";
	private String finalDBUserName = "7";
	private String finalDBPassword = "8";
	private String finalDBHost = "9";
	private String finalDBPort = "10";
	private String rabbitMQHost = "11";

	ISettings settings;

	@org.junit.Before
	public void setUp() throws Exception {
		settings = ServiceLocatorAdapter.getInstance().getObject(ISettings.class);
		settings.setParams(
				intermediateDBName, intermediateDBUserName,
				intermediateDBPassword, intermediateDBHost, intermediateDBPort,
				finalDBName, finalDBUserName, finalDBPassword, finalDBHost,
				finalDBPort, rabbitMQHost
		);
	}

	@org.junit.Test
	public void CompareIntermediateDBNameWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getIntermediateDBName();

		//Assert
		Assert.assertEquals(intermediateDBName, returnedValue);
	}

	@org.junit.Test
	public void CompareIntermediateDBHostWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getIntermediateDBHost();

		//Assert
		Assert.assertEquals(intermediateDBHost, returnedValue);
	}

	@org.junit.Test
	public void CompareIntermediateDBPortWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getIntermediateDBPort();

		//Assert
		Assert.assertEquals(intermediateDBPort, returnedValue);
	}

	@org.junit.Test
	public void CompareIntermediateDBUserNameWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getIntermediateDBUserName();

		//Assert
		Assert.assertEquals(intermediateDBUserName, returnedValue);
	}

	@org.junit.Test
	public void CompareIntermediateDBPasswordWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getIntermediateDBPassword();

		//Assert
		Assert.assertEquals(intermediateDBPassword, returnedValue);
	}



	@org.junit.Test
	public void CompareFinalDBNameWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getFinalDBName();

		//Assert
		Assert.assertEquals(finalDBName, returnedValue);
	}

	@org.junit.Test
	public void CompareFinalDBHostWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getFinalDBHost();

		//Assert
		Assert.assertEquals(finalDBHost, returnedValue);
	}

	@org.junit.Test
	public void CompareFinalDBPortWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getFinalDBPort();

		//Assert
		Assert.assertEquals(finalDBPort, returnedValue);
	}

	@org.junit.Test
	public void CompareFinalDBUserNameWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getFinalDBUserName();

		//Assert
		Assert.assertEquals(finalDBUserName, returnedValue);
	}

	@org.junit.Test
	public void CompareFinalDBPasswordWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getFinalDBPassword();

		//Assert
		Assert.assertEquals(finalDBPassword, returnedValue);
	}


	@org.junit.Test
	public void CompareRabbitMQHostWithReturnedValueBySettings() throws Exception {
		//Arrange

		//Act
		String returnedValue = settings.getRabbitMQHost();

		//Assert
		Assert.assertEquals(rabbitMQHost, returnedValue);
	}

}