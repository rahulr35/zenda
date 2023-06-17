package com.util;

import java.io.File;
import java.util.List;
import com.creditdatamw.zerocell.Reader;

public class ExcelReader {

    private ExcelReader() {}

    private static List<ExcelTestData> testData = null;

    static {
        testData = Reader.of(ExcelTestData.class)
                .from(new File(""))
                .sheet("")
                .skipHeaderRow(true)
                .list();
    }
}
