package gui;

import java.awt.Font;
import java.io.InputStream;

public class FontClass {
	
	public static Font MuseoSans500(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans_500.otf"));
	        font = font.deriveFont(Font.PLAIN, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans500Italic(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans_500_Italic.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans700(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans_700.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans900(int size) {
	    try {
	    	
	        Font font = Font.createFont(Font.TRUETYPE_FONT,FontClass.class.getResourceAsStream("/font/MuseoSans-100.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans100(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-100.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans300(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-300.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans100Italic(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-100Italic.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans300Italic(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-300Italic.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans700Italic(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-700Italic.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static Font MuseoSans900Italic(int size) {
	    try {
	        Font font = Font.createFont(Font.TRUETYPE_FONT, FontClass.class.getResourceAsStream("/font/MuseoSans-900Italic.otf"));
	        font = font.deriveFont(Font.BOLD, size);
	        return font;
	    } catch (Exception ex) {
	    }
	    return new Font("Tahoma", Font.BOLD, size);
	}

}
