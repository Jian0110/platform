package cn.com.ssj.mybatis.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.PageHelper;

import cn.com.ssj.dto.DataTablesRequest;

public class PaginationHelper {
	
	protected void pagination(DataTablesRequest dataTablesRequest) {
		String orderByString = null;
		//每页显示数，如果设为负数，那就不进行分页，就是全部显示
		if(dataTablesRequest.getLength() > 0 ) {
			PageHelper.startPage((dataTablesRequest.getStart()/ dataTablesRequest.getLength()) + 1, dataTablesRequest.getLength());
		}
		//排序处理
		if(dataTablesRequest.getOrder() != null) {
			StringBuilder orderString = new StringBuilder();
			List<Map<String,Object>> orderList = dataTablesRequest.getOrder();
			List<Map<String,Object>> columnsList = dataTablesRequest.getColumns();
			for(Map<String,Object> orderItem : orderList ) {
				int column = (int) orderItem.get("column");
				String dir = (String) orderItem.get("dir");
				Map<String,Object> columnObject = columnsList.get(column);
				boolean orderable = (boolean) columnObject.get("orderable");
				if(!orderable) continue;
				String fieldName = null;

				//取到要排序的字段名，
				//优先取name
				if(columnObject.get("name") != null) {
					fieldName = columnObject.get("name").toString();
				}else if(columnObject.get("data") != null) {
					fieldName = columnObject.get("data").toString();
				}

				if(StringUtils.isNotBlank(fieldName)) {
					String filedStr = fieldName;
					if(filedStr.contains(".")){
						filedStr = fieldName.substring(0,fieldName.lastIndexOf(".")+1) + "`" + fieldName.substring(fieldName.lastIndexOf(".")+1) + "`";
					}else{
						filedStr = "`" + filedStr + "`";
					}
					orderString.append(filedStr).append(" ").append(dir).append(",");
				}
			}
			
			if(orderString.length() > 0) {
				orderByString = orderString.substring(0,orderString.length() - 1);
				PageHelper.orderBy(orderByString);
			}
		}
	}

}
