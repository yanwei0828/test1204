package com.gtmc.carapp.service.workorder.util;

import com.infoservice.filestore.FileStoreException;
import com.infoservice.filestore.config.ConfigParser;
import com.infoservice.filestore.config.DataNodeInfo;
import com.infoservice.filestore.config.NameNodeInfo;
import com.yonyou.cloud.common.reflection.SpringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class XmlConfigParserCopy implements ConfigParser {

	public XmlConfigParserCopy() throws FileStoreException {
		Environment env=(Environment) SpringUtil.getBean("environment");
		String profile=env.getActiveProfiles()[0];
		String configPath="";
		if("dev".equals(profile)){
			configPath = "/DevFileStore.xml";
		}else if("test".equals(profile)){
			configPath = "/TestFileStore.xml";
		}else if("pro".equals(profile)){
			configPath = "/ProFileStore.xml";
		}else{
			configPath= "error";
		}		
		nameNodeMap = new HashMap();
		logger.info((new StringBuilder("Read FileServiceImpl Config : ")).append(configPath).toString());
		java.io.InputStream in = super.getClass().getResourceAsStream(configPath);
		if (in == null)
			throw new FileStoreException("create Reader Failed,file server config file not exist!");
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(in);
			List list = doc.selectNodes("/FileServer/NameNode");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if ("true".equalsIgnoreCase(((Element) obj).valueOf("@enable"))) {
					NameNodeInfo nameNodeInfo = parseNameNode((Element) obj);
					if (nameNodeMap.containsKey(nameNodeInfo.getId()))
						throw new FileStoreException(
								(new StringBuilder("Name node id invalid : ")).append(nameNodeInfo.getId()).toString());
					nameNodeMap.put(nameNodeInfo.getId(), nameNodeInfo);
				}
			}

		} catch (Exception e) {
			logger.error("parse file store config error", e);
			nameNodeMap = null;
		}
	}

	private NameNodeInfo parseNameNode(Element ele) throws Exception {
		String id = ele.valueOf("@id");
		String domain = ele.valueOf("@domain");
		NameNodeInfo nameNodeInfo = new NameNodeInfo(id, domain, true);
		logger.info((new StringBuilder("Add Name Node : [id=")).append(nameNodeInfo.getId()).append(";domain=")
				.append(nameNodeInfo.getDomain()).append("]").toString());
		List list = ele.selectNodes("./DataNode");
		DataNodeInfo dn;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); logger
				.info((new StringBuilder("Add Data Node : [id=")).append(dn.getId()).append(";protocol=")
						.append(dn.getProtocol()).append(";role=").append(dn.getRole()).append("]").toString())) {
			Object obj = iterator.next();
			dn = parseDataNode((Element) obj);
			nameNodeInfo.addDataNode(dn);
		}

		return nameNodeInfo;
	}

	private DataNodeInfo parseDataNode(Element ele) throws Exception {
		String id = ele.valueOf("@id");
		String protocol = ele.valueOf("@protocol");
		String role = ele.valueOf("@role");
		DataNodeInfo dataNodeInfo = new DataNodeInfo(id, protocol, role);
		List childs = ele.elements();
		Element child = null;
		for (Iterator iterator = childs.iterator(); iterator.hasNext(); dataNodeInfo.addParam(child.getName(),
				child.getTextTrim())) {
			Object obj = iterator.next();
			child = (Element) obj;
		}

		return dataNodeInfo;
	}

	public HashMap getParseResult() {
		return nameNodeMap;
	}

	private static Logger logger = LogManager.getLogger(XmlConfigParserCopy.class);
	private HashMap nameNodeMap;
}
