package by.vanopiano.grodno_bus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.util.*;

public class Files {

	public static boolean Copy(String from, String to)
	{
		try {      

            File fFrom = new File(from);

            if (fFrom.isDirectory()) { // Если директория, копируем все ее содержимое

                    Van.createDir(to);

                    String[] FilesList = fFrom.list();

                    for (int i = 0; i <= FilesList.length; i++)

                            if (!Copy(from + "/" + FilesList[i], to + "/" + FilesList[i]))

                                   return false; // Если при копировании произошла ошибка

            } else if (fFrom.isFile()) { // Если файл просто копируем его

                    File fTo = new File(to);

                    InputStream in = new FileInputStream(fFrom); // Создаем потоки

                    OutputStream out = new FileOutputStream(fTo);

                    byte[] buf = new byte[1024];

                    int len;

                    while ((len = in.read(buf)) > 0) {

                            out.write(buf, 0, len);

                    }

                    in.close(); // Закрываем потоки

                    out.close();

            }

    } catch (FileNotFoundException ex) { // Обработка ошибок

    } catch (IOException e) { // Обработка ошибок

    }
		return true;
	}
	public static void Create(String fileName) throws IOException {         
        File f = new File( fileName );                                                      
        if (!f.exists()) {                                                                                                                                         
                f.createNewFile();               
            }
    }
    
    public static String Read(String fileName) throws IOException {
        String fileContent = "";
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream in      = new DataInputStream(fstream);
        BufferedReader  br      = new BufferedReader (new InputStreamReader(in));
        String strLine;            
        while ((strLine = br.readLine()) != null)   {
            fileContent += strLine + '\n';  
        }                           
        in.close();                        
        return fileContent;
    }

    public static List <String> ReadArray(String fileName) throws IOException {
        List <String> fileContent = new ArrayList <String>();
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream in      = new DataInputStream(fstream);
        BufferedReader  br      = new BufferedReader (new InputStreamReader(in));
        String strLine;    
        while ((strLine = br.readLine()) !=null)   {
        	fileContent.add(strLine);
        }           
        in.close();                        
        return fileContent;
    }
    
    public static void Update(String fileName, String info) throws IOException {
        File file = new File(fileName); 
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write(info);  
        output.close();  
    }

    
    
    public static void Delete(String fileName) throws IOException
		{         
        File f = new File( fileName );                                                      
        if (f.exists()) f.delete();               
            
		}   

	static String ans;
	public static String getRandSong(String dir) throws Exception {		
	    File file = new File(dir);  
	    File[] files = file.listFiles();
	    Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(files.length);
	        if (files[randomInt].isDirectory()) getRandSong(files[randomInt].toString());
	        if (files[randomInt].isFile()) {          		 	        		
	                 ans =  files[randomInt].toString();
	        }
	        return ans;
	   	}
	
}
