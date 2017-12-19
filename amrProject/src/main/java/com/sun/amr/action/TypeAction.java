package com.sun.amr.action;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.amr.adapter.AbstractActionAdapter;
import com.sun.amr.service.ISubtypeService;
import com.sun.amr.vo.Subtype;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/pages/type/*")
public class TypeAction extends AbstractActionAdapter{
	@Resource
	private ISubtypeService subtypeService;
	
	@RequestMapping("getSubtype")
	public void getSubtype(int tid,HttpServletResponse response) {
		try {
			List<Subtype> all=this.subtypeService.list(tid);
			Iterator<Subtype> iter=all.iterator();
			//创建一个json对象数组，保存的是json对象
			JSONArray array=new JSONArray();
			while(iter.hasNext()) {
				Subtype sub=iter.next();
				//创建一个json对象
				JSONObject temp=new JSONObject();
				//将子类别的编号保存到json对象中
				temp.put("stid", sub.getStid());
				//将子类别的名称保存到json对象中
				temp.put("title", sub.getTitle());
				//将json对象保存到json数据中
				array.add(temp);
			}
			//将json的数组输出
			super.print(response, array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
