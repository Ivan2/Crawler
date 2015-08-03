package classes.ioc;

import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.date.IDateTime;
import interfaces_abstracts.db.FinalDB;
import interfaces_abstracts.db.IntermediateDB;
import interfaces_abstracts.json.IJSONParser;
import interfaces_abstracts.services.IServices;
import interfaces_abstracts.settings.ISettings;
import interfaces_abstracts.settings.ISettingsLoadManager;
import interfaces_abstracts.vk.IVkLoader;
import interfaces_abstracts.vk.IVkWallLoader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCAdapter {

	private static IoCAdapter instance = null;

	public static IoCAdapter getInstance() {
		if (instance == null)
			instance = new IoCAdapter();
		return instance;
	}

	private final String IPOST_INFO_ID = "IPostInfo";
	private final String IDATE_TIME_ID = "IDateTime";
	private final String FINAL_DB_ID = "FinalDB";
	private final String INTERMEDIATE_DB_ID = "IntermediateDB";
	private final String IJSON_PARSER_ID = "IJSONParser";
	private final String ISERVICES_ID = "IServices";
	private final String ISETTINGS_ID = "ISettings";
	private final String ISETTINGS_LOAD_MANAGER_ID = "ISettingsLoadManager";
	private final String IVK_LOADER_ID = "IVkLoader";
	private final String IVK_WALL_LOADER_ID = "IVkWallLoader";

	private ClassPathXmlApplicationContext ioc;

	public IPostInfo getIPostInfoObject() {
		return (IPostInfo)ioc.getBean(IPOST_INFO_ID);
	}

	public IDateTime getIDateTimeObject() {
		return (IDateTime)ioc.getBean(IDATE_TIME_ID);
	}

	public FinalDB getFinalDbObject() {
		return (FinalDB)ioc.getBean(FINAL_DB_ID);
	}

	public IntermediateDB getIntermediateDbObject() {
		return (IntermediateDB)ioc.getBean(INTERMEDIATE_DB_ID);
	}

	public IJSONParser getIJSONParserObject() {
		return (IJSONParser)ioc.getBean(IJSON_PARSER_ID);
	}

	public IServices getIServicesObject() {
		return (IServices)ioc.getBean(ISERVICES_ID);
	}

	public ISettings getISettingsObject() {
		return (ISettings)ioc.getBean(ISETTINGS_ID);
	}

	public ISettingsLoadManager getISettingsLoadManagerObject() {
		return (ISettingsLoadManager)ioc.getBean(ISETTINGS_LOAD_MANAGER_ID);
	}

	public IVkLoader getIVkLoaderObject() {
		return (IVkLoader)ioc.getBean(IVK_LOADER_ID);
	}

	public IVkWallLoader getIVkWallLoaderObject() {
		return (IVkWallLoader)ioc.getBean(IVK_WALL_LOADER_ID);
	}

	private IoCAdapter() {
		ioc = new ClassPathXmlApplicationContext(new String[] {"IoC.xml"});
	}

	public Object getObject(String id) {
		return ioc.getBean(id);
	}
}
