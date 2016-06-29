package com.wxq.parser.operations;
public class StrBinaryTurn {
    //将字符串转换成二进制字符串，以空格相隔
    public String toBinary(String str){
        char[] strChar = str.toCharArray();
        String result = "";
        for(int i = 0; i < strChar.length; i++){
          
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
      
    }
    //将二进制字符串转换成Unicode字符串
    public String toStr(String binStr){
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for(int i = 0; i < tempStr.length; i++){
            tempChar[i] = toChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }
    //将二进制字符串转换为char
    private char toChar(String binStr){
        int[] temp = binStrToIntArray(binStr);
        int sum = 0;
      
        for(int i = 0; i < temp.length; i++){

            sum += temp[temp.length - 1 - i] << i;

        }
  
        return (char)sum;
      
    }
    //将初始二进制字符串转换成字符串数组，以空格相隔
    private String[] StrToStrArray(String str){
        return str.split(" ");
    }
  

    //将二进制字符串转换成int数组
    private int[] binStrToIntArray(String binStr){
      
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
  
        for(int i = 0; i < temp.length; i++){
            result[i] = temp[i] - 48;
        }
        return result;
      
    }
  
    public static void main(String[] args){
      
        StrBinaryTurn cTob = new StrBinaryTurn();
        System.out.println(cTob.toBinary("橘子，好吃！aaa"));
        System.out.println();
        System.out.println(cTob.toBinary("999111"));
        System.out.println();
        System.out.println(cTob.toBinary("What a nice day!"));
        System.out.println();
        System.out.println(cTob.toBinary("^@^ - -! （）★"));
      
        System.out.println();
        System.out.println();
      
        System.out.println(cTob.toStr("110101001011000 101101101010000 " +
                "1111111100001100 101100101111101 " +
                "101010000000011 1111111100000001 " +
                "1100001 1100001 1100001 "));
        System.out.println(cTob.toStr("111001 111001 111001 " +
                "110001 110001 110001"));
        System.out.println(cTob.toStr("1010111 1101000 1100001 " +
                "1110100 100000 1100001 100000 1101110 " +
                "1101001 1100011 1100101 100000 1100100 " +
                "1100001 1111001 100001 "));
        System.out.println(cTob.toStr("1011110 1000000 1011110 " +
                "100000 101101 100000 101101 100001 100000 " +
                "1111111100001000 1111111100001001 10011000000101 "));
    }
    /**
     * 转化十六进制编码为字符串
     * @param s 十六进制编码
     * @return	指定的"Utf-8"编码格式的字符串
     */
    public String toStringHex(String s) {
    	byte[] baKeyword = new byte[s.length()/2];
    	for(int i = 0; i < baKeyword.length; i++)
    	{
    		try
    		{
    			baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	try
    	{
    		s = new String(baKeyword, "Utf-8");//UTF-16le:Not
    	}
    	catch (Exception e1)
    	{
    		e1.printStackTrace();
    	}
    	return s;
    }
}