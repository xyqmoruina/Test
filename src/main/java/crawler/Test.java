package crawler;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="http://www.bbc.com/culturevxcz/vcxzvz";
		String str2=str.replace("/", "\\/");
		String regex="http:\\/\\/www.bbc.com.*";
		System.out.println(str);
		System.out.println(str2);
		System.out.println(str.matches(regex));
	}

}
