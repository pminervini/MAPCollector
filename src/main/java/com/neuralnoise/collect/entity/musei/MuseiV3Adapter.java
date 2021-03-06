package com.neuralnoise.collect.entity.musei;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.neuralnoise.collect.IAdapter;
import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.entity.Entity;
import com.neuralnoise.collect.entity.musei.util.MuseiV3EntityParser;

public class MuseiV3Adapter implements IAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(MuseiV3Adapter.class);

	private final String resource;
	
	public MuseiV3Adapter(String resource) {
		this.resource = resource;
	}
	
	public Collection<ICollectible> collect() throws Exception {
		FileInputStream fis = new FileInputStream(resource);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);

		Map<Integer, String> colNames = Maps.newHashMap();
		List<Map<String, String>> content = Lists.newLinkedList();

		boolean first = true;

		for (Row row : sheet) {
			Map<String, String> map = (!first ? new HashMap<String, String>() : null);

			for (Cell cell : row) {
				String str = "";

				switch (cell.getCellType()) {
				case 0: {
					str = Long.toString(Math.round(cell.getNumericCellValue()));
				}
					break;
				default: {
					str = cell.getStringCellValue();
				}
					break;
				}

				if (first) {
					colNames.put(cell.getColumnIndex(), str);
				} else {
					if (str.length() > 0) {
						final String colName = colNames.get(cell.getColumnIndex());
						map.put(colName, str);
					}
				}
			}

			first = false;

			if (map != null) {
				if (map.keySet().size() > 0) {
					content.add(map);
				}
			}
		}

		Collection<ICollectible> entities = Lists.newLinkedList();
		for (Map<String, String> map : content) {
			log.info("Content: " + map);
			Entity entity = MuseiV3EntityParser.parse(map);
			
			if (entity != null) {
				entities.add(entity);
			}
			
			log.info("Entity: " + entity);
		}
		
		return entities;
	}
	
	public static void main(String[] args) throws Exception {
		String resource = "tools/musei/MuseiV3.xlsx";
		IAdapter adapter = new MuseiV3Adapter(resource);
		Collection<ICollectible> entities = adapter.collect();
	}
	
}
