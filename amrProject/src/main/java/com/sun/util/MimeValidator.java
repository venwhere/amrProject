package com.sun.util;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
public class MimeValidator {
	public static boolean isMime(Object obj, HttpServletRequest request) { // �Ƿ�ָ����mime����
		// 1������ȷ�����Ƿ����ϴ��ļ�
		MultipartResolver mr = new CommonsMultipartResolver(); // ׼�������ϴ��ļ��Ĳ�������
		if (mr.isMultipart(request)) {	// ��ǰ�������ϴ��ļ�
			// 2��ȡ�ö����mime��֤����
			String mimeContent = MessageUtil.getMessage(obj, "mimeType");
			if (mimeContent == null || "".equals(mimeContent)) { // ��ʾ����û�й���
				return true;
			} else {	// ��Ҫ�����ÿһ���������й����ƥ����
				String mimeRules [] = mimeContent.split("\\|") ;// ȡ��ȫ��������
				MultipartRequest mreq = (MultipartRequest) request ;	// �����ϴ���request
				Map<String, MultipartFile> map = mreq.getFileMap(); // ȡ��ȫ�����ϴ�����
				if (map.size() > 0) {	// ��ǰ�ı������Ѿ�������ָ�����ϴ��������
					Iterator<Map.Entry<String, MultipartFile>> iter = map.entrySet().iterator() ;
					while (iter.hasNext()) {
						Map.Entry<String, MultipartFile> me = iter.next() ;
						// System.out.println("�������ƣ�" + me.getKey() + "���ļ���С��" + me.getValue().getSize());
						if (me.getValue().getSize() > 0 ) {	// ������������ı����ļ��ϴ�����ô����Ҫ�������͵�ƥ��
//							System.out.println("** mime = " + me.getValue().getContentType());
//							System.out.println("** rules = " + Arrays.toString(mimeRules));
//							System.out.println("** �ж� = " + ValidatorUtil.isMime(mimeRules, me.getValue().getContentType()));
							if (!ValidatorRules.isMime(mimeRules, me.getValue().getContentType())) {
								return false ;
							}
						}
					}
				}
			} 
		}
		return true ;
	}
}
