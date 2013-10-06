package be.rochus.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

	public static String normalize(String input) {
		if(input == null) {
			return null;
		}
		String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public static String toUrlTitle(String input) {
		if(input == null) {
			return null;
		}
		return normalize(input).toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9\\-]", "");
	}
	
}
