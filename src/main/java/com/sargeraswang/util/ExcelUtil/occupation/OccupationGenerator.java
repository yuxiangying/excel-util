package com.sargeraswang.util.ExcelUtil.occupation;

import java.io.InputStream;

public interface OccupationGenerator {

    String toJson(InputStream inputStream);

    boolean createJsonFile(String jsonString, String filePath, String fileName);

}
