package com.gtmc.carapp.service.workorder.util;

import com.infoservice.filestore.FileStore;
import com.infoservice.filestore.FileStoreException;
import com.infoservice.filestore.config.ConfigParser;
import com.infoservice.filestore.impl.FileStoreImpl;

import java.util.HashMap;

public class FileStoreCopy {
	public FileStoreCopy() {
	}

	public static FileStore getInstance() throws FileStoreException {
		if (fs == null)
			synchronized (com.gtmc.carapp.service.workorder.util.FileStoreCopy.class) {
				if (fs == null) {
					ConfigParser xmlconf = new XmlConfigParserCopy();
					nameNodeMap = xmlconf.getParseResult();
					fs = new FileStoreImpl(nameNodeMap);
				}
			}
		return fs;
	}

	private static FileStore fs = null;
	private static HashMap nameNodeMap = null;

}
