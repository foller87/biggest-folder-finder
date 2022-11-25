import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "data/Метро Москвы.html";
        File file = new File(folderPath);

        // подсчет объема папки через ForkJoinPool
        FolderSizeCalculator folderSizeCalculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(folderSizeCalculator);
        System.out.println(size);

    }

    public static long getFolderSize(File folder) // подсчет объема папки обычным методом в 1 поток
    {
        if(folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files) sum +=getFolderSize(file);

        return sum;
    }
}
