package com.watchmoreonline;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.watchmoreonline.serials.Serial;
import com.watchmoreonline.serials.SerialDao;
import com.watchmoreonline.services.Responses;
import com.watchmoreonline.services.UserExcelExporter;


@RestController
public class ExcelController {

	@Autowired
	SerialDao serialDao;
	
	@Autowired
	Responses responses;
	
	@GetMapping("/")
	public String index() {
		return "Greetings fr!";
	}
	
	public static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();
		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}
	
	@PostMapping("/uploadEpisodeByExcel")
	public Object uploadEpisodeByExcel ( @RequestBody MultipartFile file  ,String id , HttpServletRequest req) throws IOException {
	    try{
			Serial s = serialDao.find(id) ;
			List<String>names = new ArrayList<String>();
			List<String>episodes = new ArrayList<String>();
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;
            while ( rows.hasNext()) {
                Row currentRow = rows.next();
                if (isRowEmpty(currentRow)) {
                    continue;
                }
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                String col1 = null;
                String col2 = null;
                int cellIdx = 0;
                DataFormatter formatter = new DataFormatter();
                while (cellsInRow.hasNext() || cellIdx < 2) {
                    if (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                    }                   
                    Cell cell = currentRow.getCell(cellIdx);
                    switch (cellIdx) {
                        case 0:if(cell!= null) {
                            if(cell.getCellType() != CellType.BLANK) {
                                col1= formatter.formatCellValue(cell).trim();    
                                names.add(col1);
                            }
                        }break;
                        case 1:if(cell!= null) {
                            if(cell.getCellType() != CellType.BLANK) {
                                col2= formatter.formatCellValue(cell).trim();
                                episodes.add(col2);
                            }
                        }break;
                        default:break;
                    }
                    cellIdx++;
                } 
                System.out.println(col1+"      "+col2);
            }      
            s.setEname(names);
            s.setElink(episodes);
            serialDao.update(s);
            return responses.setMsg(s);
        }
		catch ( Exception e ) {
            return responses.setMsg(e.getMessage());
 
        }
 	}
	
	
    @GetMapping("downloadEpisodeInExcel")
    public void downloadEpisodeInExcel(HttpServletResponse response , String id) throws IOException {
    	Serial s = serialDao.find(id) ;
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());      
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+s.getName()+".xlsx";
        response.setHeader(headerKey, headerValue);       
		List<String>names = s.getEname();
		List<String>links = s.getElink();	
        UserExcelExporter excelExporter = new UserExcelExporter(names,links);
        excelExporter.export(response); 
    }
}
