import java.io.*;
import java.util.ArrayList;

public class Clean {
    public String FileOsn="C:\\Users\\Евгений\\IdeaProjects\\Moi\\KeyClean\\src\\Key.txt";
    public String FileDub;
    private ArrayList<String> MasOsn;
    private ArrayList<String> MasDub;
    private  String barer="EndFileSize=";

    public void CleanFile1 (){
// String str = new String(string.getBytes("Cp1251"),"Cp866");
            try (FileInputStream file1=new FileInputStream(FileOsn)){

              Reader read= new InputStreamReader(file1,"UTF-8");



                     // Определяем размер ArrayList по 5 строкам
                    int strok = 0, ch, simvol=0, simvolMax=0;

                    while ((ch = read.read()) != -1) {


                        simvol++;
                        if ((char) ch == '\n') {
                        strok++;
                        if(simvol>simvolMax) simvolMax=simvol;
                        }
                        if (strok > 5){

                            break;
                        }

                    }
                FileInputStream file2=new FileInputStream(FileOsn);
               read= new InputStreamReader(file2,"UTF-8");
                    int arraySize=file1.available()/(simvolMax);
                    MasOsn=new ArrayList<String>(arraySize);
                    char []masch=new char[40];
                    simvol=0;

                    while ((ch=read.read())!=-1){

                        if((char)ch!=' '){
                            masch[simvol]=(char)ch;
                            simvol++;
                        }
                        if(simvol>39) break;
                    }

                System.out.println(masch);

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void main(String[] args) {
        Clean n1=new Clean();
        n1.CleanFile1();


    }
}
