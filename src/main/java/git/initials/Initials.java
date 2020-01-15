package git.initials;

public class Initials {
	
	public static String getInitials(String name) {
        StringBuilder initials = new StringBuilder();
        boolean addNext = true;
        if (name != null) {
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (c == ' ' || c == '-' || c == '.') {
                    addNext = true;
                } else if (addNext) {
                    initials.append(c);
                    addNext = false;
                }
            }
        }
        return initials.toString().toUpperCase();
    }
}
