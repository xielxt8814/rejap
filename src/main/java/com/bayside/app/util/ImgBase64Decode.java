package com.bayside.app.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImgBase64Decode {
	  public static boolean generateImage(String imgStr, String imgFilePath) {
			if (imgStr == null){
				return false;
			}
			try {
				byte[] b = Base64.decodeBase64(imgStr);
				for(int i=0; i<b.length; ++i) {
					if(b[i]<0) {
						b[i]+=256;
					}
				}
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				return true;
	
			} catch (Exception e) {
				return false;
			}
	  }
}
