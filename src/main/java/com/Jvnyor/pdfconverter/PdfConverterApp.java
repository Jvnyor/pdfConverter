package com.Jvnyor.pdfconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import io.github.jonathanlink.PDFLayoutTextStripper;

public class PdfConverterApp {
	
	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */
	
	public static void main(String[] args) {

		System.out.println("\n"
				+ ".---. .---. .---.    .--.  .--. .-..-..-..-. .--. .---. .-----. .--. .---. \r\n"
				+ ": .; :: .  :: .--'  : .--': ,. :: `: :: :: :: .--': .; :`-. .-': .--': .; :\r\n"
				+ ":  _.': :: :: `;    : :   : :: :: .` :: :: :: `;  :   .'  : :  : `;  :   .'\r\n"
				+ ": :   : :; :: :     : :__ : :; :: :. :: `' ;: :__ : :.`.  : :  : :__ : :.`.\r\n"
				+ ":_;   :___.':_;     `.__.'`.__.':_;:_; `.,' `.__.':_;:_;  :_;  `.__.':_;:_;");
		
		char nextLine = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		do {
			
			try {
			        	
				String pdfPath = null;
						
				do {
					
					System.out.print("\nInsert the .pdf file path in computer: ");
					
					pdfPath = scanner.nextLine();
					
				} while (!pdfPath.contains(".pdf") && pdfPath == null);
				
				byte[] pdfPathBytes = pdfPath.getBytes();
						
				String pdfPathToUTF8 = new String(pdfPathBytes, StandardCharsets.UTF_8);
						
				File file = new File(pdfPathToUTF8);
					
				String txtPath = null;
						
				do {
					
					System.out.print("\nInsert the .txt file path to save into computer: ");
					
					txtPath = scanner.nextLine();
					
				} while (!txtPath.contains(".txt") && txtPath == null);
				
				byte[] txtPathBytes = txtPath.getBytes();
						
				String txtPathToUTF8 = new String(txtPathBytes, StandardCharsets.UTF_8);
						
				PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
						
				pdfParser.parse();
						
				PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
						
				PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
						
				String string = pdfTextStripper.getText(pdDocument);
						        
				PrintWriter out = new PrintWriter(new FileOutputStream(txtPathToUTF8));
						
				String lines[] = string.split("\\r?\\n");
						
				for (String line : lines) {	
							
					out.println(line);
							
				}
						
				if (out != null) {
					
					System.out.println("\nFile created in "+txtPath);
					
				}
						
				out.flush();
				
				out.close();
						
				pdDocument.close();
				
				System.out.print("\nDo you wants new conversion? Y/N: ");
						
				nextLine = scanner.nextLine().charAt(0);
						
		    } catch (FileNotFoundException e) {
					
				e.printStackTrace();
					
			} catch (IOException e) {
					
				e.printStackTrace();
					
			}
		     
			if (nextLine == 'N' || nextLine == 'n') {
				scanner.close();
				break;
			}
			
	    } while (nextLine == 'Y' || nextLine == 'y');
		
	}

}
