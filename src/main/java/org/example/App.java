package org.example;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        System.out.println( "Hello World!" );
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(
                FileUtil.file("D:\\Authing\\TempCode\\Ingress\\logging-2022-11-15_19-55-19.csv"));
        List<CsvRow> rows = data.getRows();
        CsvWriter writer = CsvUtil.getWriter("D:\\Authing\\TempCode\\Ingress\\logging-new.csv",
                CharsetUtil.CHARSET_UTF_8);
        CsvRow header = rows.get(0);
        String[] headerStr = new String[header.size()];
        header.toArray(headerStr);
        writer.writeLine(headerStr);
        for (int i = 1; i < rows.size(); i++) {
            CsvRow row = rows.get(i);
            List<String> rawList = row.getRawList();
            String timestamp = rawList.get(2);
            
            //String subTimestamp = timestamp.substring(0, 11);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long date_temp = Long.parseLong(timestamp);
            String date_string = sdf.format(new Date(date_temp));
    
            rawList.set(2, date_string);
            String[] rowStr = new String[rawList.size()];
            rawList.toArray(rowStr);
            writer.write(rowStr);
        }
    
        writer.close();
    }
}
