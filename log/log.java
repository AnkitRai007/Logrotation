import java.io.File;
import java.lang.String;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class C24NewFileWriter
{
    public static void main (String[] args) throws IOException,InterruptedException
    {
        int sleep = 0;
        final Map<String, List<String>> params = new HashMap<>();

        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                params.put(a.substring(1), options);
            }
            else if (options != null) {
                options.add(a);
            }
            else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }
        System.out.printf("Sleep time set to - %s ms%n", params.get("sleep").get(0));

        BufferedReader in = new BufferedReader(new FileReader("E:/logstest/con24_log40.txt"));
        String str="";
        StringBuilder lines= new StringBuilder();
        while((str = in.readLine()) != null)
        {
            lines.append(str);
            lines.append("\n");
        }

        int count = 0;
        for (int j=0;j>=0;j++)
        {
            if(j>0 && j<=4) {
                count++;
            }
            String filename="E:/logstest/con24_log40"+j+".txt";
            File file= new File(filename);
            FileWriter fileWriter = new FileWriter(file, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Date dateobj_start = new Date();
            for (int i=0; i<150; i++)
            {
                int s = Integer.parseInt(params.get("sleep").get(0));
                Thread.sleep(s);
                int z=8000000+i;
                String k= String.valueOf(z);
                String rep_lines= lines.toString().replace("918918226573",k);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
                String rep_start_date = rep_lines.replace("07/04/2020 17:52:03.394", String.valueOf(df.format(dateobj_start)));
                Date dateobj_end = new Date();
                String rep_end_date = rep_start_date.replace("07/04/2020 17:52:04.511", String.valueOf(df.format(dateobj_end)));

                bufferedWriter.write(rep_end_date);


                // Thread.sleep(1000);
                if (i%2==0)
                {
                    Thread.sleep(100);
                }
            }
            Thread.sleep(1000);
            bufferedWriter.close();
            fileWriter.close();
            if(count >= 4) {
                File f = new File("E:/logstest/con24_log40"+(j -4)+".txt");
                f.delete();
            }
        }

    }
}
