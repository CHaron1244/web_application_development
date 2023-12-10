package cn.itcast;
import java.io.UnsupportedEncodingException;
public class CharactorEncoding {
    public CharactorEncoding() {
    }
    /**
     * 对字符进行转码处理
     * @param str  要转码的字符串
     * @return 编码后的字符串
     */
    public String toString(String str) {
        String text= "";
        if (str!=null&&!"".equals(str)){
            try{
                text = new String(str.getBytes("ISO-8859-1"),"UTF-8");
            }catch (UnsupportedEncodingException e){
            }
        }
        return text;
    }
}
