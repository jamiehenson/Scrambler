public class Scramble 
{
	public static String scramble (String input, String key)
	{
		StringBuffer outbuff = new StringBuffer();
		
		for (int i = 0, j = 0; i < input.length(); i++,j++) {
            char c = input.charAt(i);
			c += Character.getNumericValue(key.charAt(j%key.length()));;
			outbuff.append(c);
		}
		return outbuff.toString();
	}
	
	public static String descramble (String input, String key)
	{
		StringBuffer outbuff = new StringBuffer();
		
		for (int i = 0, j = 0; i < input.length(); i++,j++) {
            char c = input.charAt(i);
			c -= Character.getNumericValue(key.charAt(j%key.length()));;
			outbuff.append(c);
		}
		return outbuff.toString();
	}
}
